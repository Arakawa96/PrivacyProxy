package org.c2h4.plugin.privacy

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by jinbangyu on 2022/5/19.
 */
class PrivacyHookPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)

        androidComponents.onVariants { variant ->
            variant.transformClassesWith(
                PrivacyClassVisitorFactory::class.java,
                InstrumentationScope.ALL
            ) {
            }
            variant.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)

        }
    }
}