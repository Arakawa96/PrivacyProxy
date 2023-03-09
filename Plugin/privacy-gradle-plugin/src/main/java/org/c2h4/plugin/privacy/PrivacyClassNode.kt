package org.c2h4.plugin.privacy

import org.c2h4.plugin.privacy.utils.Log
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.AnnotationNode
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodInsnNode
import org.objectweb.asm.tree.MethodNode

/**
 * @Author LiABao
 * @Since 2021/10/5
 */
class PrivacyClassNode(private val nextVisitor: ClassVisitor) : ClassNode(Opcodes.ASM5) {

    override fun visitEnd() {
        super.visitEnd()
        val className = name
        val isWhite = PrivacyCreator.whiteList.firstOrNull {
            name.contains(it, true)
        }.let { it != null }
        if (!isWhite) {
            val iterator: Iterator<MethodNode> = methods.iterator()
            while (iterator.hasNext()) {
                val method = iterator.next()
                method.instructions?.iterator()?.forEach {
                    when (it) {
                        is MethodInsnNode -> {
                            it.isPrivacy()?.apply {
                                Log.info("source:${this@PrivacyClassNode.name}:before:${it.owner}，${it.name}，${it.desc}，${it.opcode}")
                                it.opcode = code
                                it.owner = owner
                                it.name = name
                                it.desc = desc
                                Log.info("source:${this@PrivacyClassNode.name}:after:${it.owner}，${it.name}，${it.desc}，${it.opcode}")
                            }
                        }
                        is AnnotationNode -> {

                        }
                    }

                }
            }
        }

        accept(nextVisitor)
    }
}


private fun MethodInsnNode.isPrivacy(): PrivacyAsmEntity? {
    val pair = PrivacyCreator.privacyList.firstOrNull {
        val first = it.first
        first.owner == owner && first.code == opcode && first.name == name && first.desc == desc
    }
    return pair?.second
}