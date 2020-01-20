# pixiaozhi
1.0.0仅仅测试是否正常可以依赖（TextViewUtil）

gradle依赖

Step 1.Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.pixiaozhi:pixiaozhi:1.0.0'
	}
