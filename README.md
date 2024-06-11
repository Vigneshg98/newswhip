# NewsWhip URL Manager

## Overview
This application is a command line tool to manage URLs with associated social scores. It supports adding, removing URLs, and exporting statistics.

## Requirements
- Java JDK 8 or higher

## Setup Instructions
1. Clone or download the repository.
2. Open a terminal and navigate to the project directory.
3. Compile the Java files:
    ```
    javac Main.java
    ```
4. Run the application:
    ```
    java Main
    ```

## Usage
The application supports the following commands:
- `ADD <url> <score>`: Adds a URL with an associated social score.
- `REMOVE <url>`: Removes a URL from the system.
- `EXPORT`: Exports statistics about the URLs stored in the system.
- `EXIT`: Exits the application.

### Example

```
ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20
URL added.
ADD https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/ 30
URL added.
ADD http://www.bbc.com/news/world-europe-45746837 10
URL added.

EXPORT
domain;urls;social_score
rte.ie;2;50
bbc.com;1;10
```
```
REMOVE https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/
URL removed.

EXPORT
domain;urls;social_score
rte.ie;1;20
bbc.com;1;10
```
```
EXIT
```

## Notes
- Ensure URLs are properly formatted.
- Use the commands as specified to avoid errors.
