package org.c2h4.plugin.privacy

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.objectweb.asm.ClassVisitor

/**
 *
 *  @Author LiABao
 *  @Since 2021/10/5
 *
 */
abstract class PrivacyClassVisitorFactory : AsmClassVisitorFactory<InstrumentationParameters.None> {

    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        return PrivacyClassNode(nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return true
    }

}
