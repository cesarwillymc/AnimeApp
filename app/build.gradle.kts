plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.apollographql.apollo3").version("3.7.3")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    kotlin("kapt")
}
apply(from = "${rootProject.projectDir}/jacoco.gradle")

apollo {
    service("service") {
        packageName.set("com.cesarwillymc")
    }
}

android {
    namespace = "com.cesarwillymc.animeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cesarwillymc.animeapp"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Dagger
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    // Apollo
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")

    // Android KTX
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // UI
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    // Unit test
    implementation("androidx.navigation:navigation-compose:2.7.3")

    // View Model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")

    // Unit test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("app.cash.turbine:turbine:1.0.0")

    // Apollo test GraphQL
    testImplementation("com.apollographql.apollo3:apollo-mockserver:3.7.3")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
project.afterEvaluate {
    this.tasks.getByName("clean").dependsOn("installGitHooks")
    this.tasks.getByName("assemble").dependsOn("installGitHooks")
    tasks.create("executeTest") {
        group = "verification"
        dependsOn("test")
    }
    tasks.create("executeValidations") {
        group = "verification"
        dependsOn("lintKotlin")
        dependsOn("detekt")
        dependsOn("executeTest") /* Execute all test */
    }
    tasks.create("formatAndValidate") {
        group = "verification"
        dependsOn("formatKotlin")
        dependsOn("executeValidations")
        tasks.getByName("executeValidations").mustRunAfter("formatKotlin")
    }
    tasks.create("copyGitHooks", Copy::class.java) {
        description = "Copies the git hooks from team-props/git-hooks to the .git folder."
        from("${rootDir}/team-props/git-hooks") {
            include("**/*.sh")
            rename("(.*).sh", "$1")
        }
        into("${rootDir}/.git/hooks")
    }
    tasks.create("installGitHooks", Exec::class.java) {
        description = "Installs the pre-commit git hooks from team-props/git-hooks."
        group = "git hooks"
        workingDir = rootDir
        commandLine = listOf("chmod")
        args("-R", "+x", ".git/hooks/")
        dependsOn("copyGitHooks")
        doLast {
            logger.info("---- Git hook installed successfully.")
        }
    }
}