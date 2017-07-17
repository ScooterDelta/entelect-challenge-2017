# Entelect Challenge BOT - Written in Kotlin.

- Using Dagger 2 dependency injection framework.
- Written by Anthony Farquharson.

## Build Application:
- Build using maven: 
```mvn clean package```

## Project Structure
- entelect-challenge
	- pom.xml
		- Top level POM, with Kotlin build configuration
	- common/
		- Common objects (POJO), containing the Serialization state objects.
	- logging/
		- Logging configuration module, with runtime and test logging configurations
	- process/
		- Processing module, with ship placement and attack logic
	- runtime/
		- Runtime configuration, setting up dependency injection and runnable jar.

## Run application:
- Run from bot host (C# Entelect Challenge game engine), example config:
```-b "..\..\..\..\..\Sample Bots\C#\SampleBot" \path\to\entelect-challenge --pretty -l \path\to\challenge-logs```