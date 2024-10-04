plugins{
    id ("org.jetbrains.kotlin.jvm") version "1.9.24"
    kotlin("plugin.serialization") version "2.0.20"
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}