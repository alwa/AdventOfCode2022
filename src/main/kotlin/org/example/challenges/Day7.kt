package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day7 : TwoPartChallenge<Int> {

    override fun part1(file: File): Int {
        val root = VirtualDirectory(parent = null, name = "/", mutableListOf(), mutableListOf())
        var currentDir = root

        val files: MutableList<VirtualFile> = mutableListOf()
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
                                subDirectories = mutableListOf(),
                                files = mutableListOf()
                            )
                        )
                    }
                } else {
                    val existingFile = currentDir.subDirectories.find { it.name == lineParts[1] }
                    if (existingFile == null) {
                        val virtualFile =
                            VirtualFile(parent = currentDir, name = lineParts[1], size = lineParts[0].toInt())
                        var tempDir: VirtualDirectory? = currentDir
                        while (tempDir != null) {
                            tempDir.size += virtualFile.size
                            tempDir = tempDir.parent
                        }
                        currentDir.files.add(virtualFile)
                        files.add(virtualFile)
                    }
                }
            }
        }

        val allNodes = root.nodesList(root)
            .filterIsInstance<VirtualDirectory>()
            .filter { it.size() < 100_000 }
        var sum = 0
        for (node in allNodes) {
            sum += node.size()
        }
        return sum
    }

    override fun part2(file: File): Int {
        return -1
    }

    class VirtualDirectory(
        val parent: VirtualDirectory? = null,
        val name: String,
        val subDirectories: MutableList<VirtualDirectory> = mutableListOf(),
        val files: MutableList<VirtualFile> = mutableListOf(),
        var size: Int = 0
    ) : Node {

//        fun calculate() {
//            var tooBig: Boolean = false
//            var subDirSize = 0
//            var fileSize = 0
//            for (subDirectory in subDirectories) {
//                val subDirPairSize = subDirectory.calculate()
//                if (subDirPairSize.first) {
//                    tooBig = true
//                }
//                subDirSize += subDirPairSize.second
//            }
//            for (file in files) {
//                fileSize += file.size
//            }
//            size = subDirSize + fileSize
//            if (size > 100_000) {
//                tooBig = true
//            }
//            return Pair(tooBig, size)
//        }


        fun nodesList(rootRef: Node): List<Node> {
            return rootRef.children()
                .map { nodesList(it) }
                .flatten() + rootRef
        }

        override fun size(): Int = size

        override fun children(): List<Node> = subDirectories + files

    }

    class VirtualFile(val parent: VirtualDirectory? = null, val name: String, val size: Int) : Node {
        override fun size(): Int = size

        override fun children(): List<Node> = emptyList()

    }

    interface Node {

        fun size(): Int
        fun children(): List<Node>

    }

}
