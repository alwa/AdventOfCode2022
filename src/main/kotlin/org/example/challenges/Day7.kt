package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day7 : TwoPartChallenge<Int> {

    override fun part1(file: File): Int {
        val root = VirtualDirectory(parent = null, name = "/", mutableSetOf(), mutableSetOf())
        var currentDir = root
        file.forEachLine { line ->
            val lineParts = line.split(" ")
            if (lineParts[0] == "$") {
                when (lineParts[1]) {
                    "cd" -> {
                        currentDir = if (lineParts[2] == "/") {
                            root
                        } else if (lineParts[2] == "..") {
                            currentDir.parent ?: currentDir
                        } else {
                            checkNotNull(currentDir.subDirectories.find { it.name == lineParts[2] })
                        }
                    }
                    "ls" -> {
                        // Do nothing
                    }
                    else -> {
                        throw IllegalStateException()
                    }
                }
            } else {
                check(lineParts.size == 2)
                if (lineParts[0] == "dir") {
                    val directoryName = lineParts[1]
                    val existingDir = currentDir.subDirectories.find { it.name == directoryName }
                    if (existingDir == null) {
                        currentDir.subDirectories.add(
                            VirtualDirectory(
                                parent = currentDir,
                                name = directoryName,
                                subDirectories = mutableSetOf(),
                                files = mutableSetOf()
                            )
                        )
                    }
                } else {
                    val existingFile = currentDir.subDirectories.find { it.name == lineParts[1] }
                    if (existingFile == null) {
                        currentDir.files.add(
                            VirtualFile(parent = currentDir, name = lineParts[1], size = lineParts[0].toInt())
                        )
                    }
                }
            }
        }

        return root.size()
    }

    override fun part2(file: File): Int {
        return -1
    }

    class VirtualDirectory(
        val parent: VirtualDirectory? = null,
        val name: String,
        val subDirectories: MutableSet<VirtualDirectory> = mutableSetOf(),
        val files: MutableSet<VirtualFile> = mutableSetOf()
    ) {

        fun size(): Int {
            var subDirSize = 0
            var fileSize = 0
            for (subDirectory in subDirectories) {
                subDirSize += subDirectory.size()
            }
            for (file in files) {
                fileSize += file.size
            }
            val currentDirSum = subDirSize + fileSize
            return if (currentDirSum > 100_000) {
                subDirSize
            } else {
                currentDirSum + subDirSize
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as VirtualDirectory

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }

    }

    class VirtualFile(val parent: VirtualDirectory? = null, val name: String, val size: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as VirtualFile

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

}
