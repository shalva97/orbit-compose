plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("com.android.lint")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    testImplementation("com.google.android.gms:play-services-vision-common:19.1.3")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:${libs.versions.kotlin.lang.get()}")

    compileOnly("com.android.tools.lint:lint-api:30.4.0-alpha08")

    testImplementation("com.android.tools.lint:lint:30.4.0-alpha08")
    testImplementation("com.android.tools.lint:lint-tests:30.4.0-alpha08")
    testImplementation("junit:junit:4.13.2")
}