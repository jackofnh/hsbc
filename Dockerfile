# 第一阶段：使用Maven镜像来分析和复制依赖项
FROM maven:3.8.4-openjdk-11 AS deps
WORKDIR /build
COPY pom.xml .
# 这一行是关键：它会下载依赖项并复制到target/dependency/中
RUN mvn dependency:go-offline dependency:copy-dependencies -DoutputDirectory=./target/dependency

# 第二阶段：编译应用本身
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /build
COPY . .
# 编译打包，但不包含依赖（pom中配置packaging为jar，但不用maven-shade-plugin）
RUN mvn compile -DskipTests

# 第三阶段：最终的运行时镜像
FROM openjdk:11-jre-slim
WORKDIR /app

# 1. 从deps阶段复制所有依赖JAR包到一个目录
COPY --from=deps /build/target/dependency/ ./lib/
# 2. 从build阶段复制编译好的应用自己的CLASS文件
COPY --from=build /build/target/classes/ ./classes/

# 设置Classpath，告诉Java去哪里找JAR包和Class文件
ENV CLASSPATH=/app/classes:/app/lib/*

# 指定主类启动应用
ENTRYPOINT ["java", "com.gao.hsbc.DemoApplication"]