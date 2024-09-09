package io.nia

// @CacheableTask
// abstract class GenerateBadgingTask : DefaultTask() {
//
//     @get:OutputFile
//     abstract val badging: RegularFileProperty
//
//     @get:PathSensitive(PathSensitivity.NONE)
//     @get:InputFile
//     abstract val apk: RegularFileProperty
//
//     @get:PathSensitive(PathSensitivity.NONE)
//     @get:InputFile
//     abstract val aapt2Executable: RegularFileProperty
//
//     @get:Inject
//     abstract val execOperations: ExecOperations
//
//     @TaskAction
//     fun taskAction() {
//         execOperations.exec {
//             commandLine(
//                 aapt2Executable.get().asFile.absolutePath,
//                 "dump",
//                 "badging",
//                 apk.get().asFile.absolutePath,
//             )
//             standardOutput = badging.asFile.get().outputStream()
//         }
//     }
// }
//
// @CacheableTask
// abstract class CheckBadgingTask : DefaultTask() {
//
//     // In order for the task to be up-to-date when the inputs have not changed,
//     // the task must declare an output, even if it's not used. Tasks with no
//     // output are always run regardless of whether the inputs changed
//     @get:OutputDirectory
//     abstract val output: DirectoryProperty
//
//     @get:PathSensitive(PathSensitivity.NONE)
//     @get:InputFile
//     abstract val goldenBadging: RegularFileProperty
//
//     @get:PathSensitive(PathSensitivity.NONE)
//     @get:InputFile
//     abstract val generatedBadging: RegularFileProperty
//
//     @get:Input
//     abstract val updateBadgingTaskName: Property<String>
//
//     override fun getGroup(): String = LifecycleBasePlugin.VERIFICATION_GROUP
//
//     @TaskAction
//     fun taskAction() {
//         assertWithMessage(
//             "Generated badging is different from golden badging! " +
//                 "If this change is intended, run ./gradlew ${updateBadgingTaskName.get()}",
//         )
//             .that(generatedBadging.get().asFile.readText())
//             .isEqualTo(goldenBadging.get().asFile.readText())
//     }
// }
//
// private fun String.capitalized() = replaceFirstChar {
//     if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
// }
//
// fun Project.configureBadgingTasks(
//     baseExtension: BaseExtension,
//     componentsExtension: ApplicationAndroidComponentsExtension,
// ) {
//     // Registers a callback to be called, when a new variant is configured
//     componentsExtension.onVariants { variant ->
//         // Registers a new task to verify the app bundle.
//         val capitalizedVariantName = variant.name.capitalized()
//         val generateBadgingTaskName = "generate${capitalizedVariantName}Badging"
//         val generateBadging =
//             tasks.register<GenerateBadgingTask>(generateBadgingTaskName) {
//                 apk = variant.artifacts.get(SingleArtifact.APK_FROM_BUNDLE)
//
//                 aapt2Executable = File(
//                     baseExtension.sdkDirectory,
//                     "${SdkConstants.FD_BUILD_TOOLS}/" +
//                         "${baseExtension.buildToolsVersion}/" +
//                         SdkConstants.FN_AAPT2,
//                 )
//
//
//                 badging = project.layout.buildDirectory.file(
//                     "outputs/apk_from_bundle/${variant.name}/${variant.name}-badging.txt",
//                 )
//
//             }
//
//         val updateBadgingTaskName = "update${capitalizedVariantName}Badging"
//         tasks.register<Copy>(updateBadgingTaskName) {
//             from(generateBadging.get().badging)
//             into(project.layout.projectDirectory)
//         }
//
//         val checkBadgingTaskName = "check${capitalizedVariantName}Badging"
//         tasks.register<CheckBadgingTask>(checkBadgingTaskName) {
//             goldenBadging = project.layout.projectDirectory.file("${variant.name}-badging.txt")
//
//             generatedBadging = generateBadging.get().badging
//
//             this.updateBadgingTaskName = updateBadgingTaskName
//
//             output = project.layout.buildDirectory.dir("intermediates/$checkBadgingTaskName")
//
//         }
//     }
// }
//