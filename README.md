# 🚀 Web Scraping with Selenium & TestNG

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) 
![TestNG](https://img.shields.io/badge/TestNG-FF9800?style=for-the-badge&logo=testng&logoColor=white) 
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white) 
![Jackson API](https://img.shields.io/badge/Jackson-2F74C0?style=for-the-badge&logo=java&logoColor=white) 
![JSON](https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white) 
![XPath](https://img.shields.io/badge/XPath-25A5E6?style=for-the-badge&logo=code&logoColor=white)



## 📌 Overview
This project automates web scraping using **Selenium WebDriver** and **TestNG** to extract structured data from `https://www.scrapethissite.com/`.

- **Test Case 1**: Extracts hockey teams with a win percentage below 40%.
- **Test Case 2**: Extracts Oscar-winning films and their nominations, sorting by awards.
- Saves extracted data in JSON format.

---

## 📂 Project Structure
```
├── src/
│   ├── demo/
│   │   ├── TestCases.java       # Main test cases
│   │   ├── wrappers/
│   │   │   ├── Wrappers.java   # Helper functions
│   ├── resources/
│   │   ├── logging.properties  # Logging configuration
├── build/
│   ├── chromedriver.log         # Selenium logs
├── test-output/                 # TestNG output files
├── hockey-team-data.json        # Scraped hockey data
├── oscar-winner-data.json       # Scraped Oscar data
├── README.md                    # Project Documentation
```

---

## 📦 Dependencies
Make sure you have the following dependencies installed:
- **Java** (JDK 8+)
- **Gradle** (for dependency management)
- **Selenium WebDriver**
- **TestNG**
- **Jackson** (for JSON processing)

### Install Dependencies via Gradle
```sh
    //testng dependency
    testImplementation 'org.testng:testng:6.9.10'

    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    implementation 'org.seleniumhq.selenium:selenium-java:4.21.0'

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.3")
```

---

## 🛠 Setup & Execution
### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Gourab-Pal/scrapper-automation.git
cd scrapper-automation
```
### 2️⃣ Configure ChromeDriver
Ensure **ChromeDriver** is installed and matches your browser version. Alternatively we can use **WebDriverManager** or latest **Selenium** to avaoid these additional installations.

### 3️⃣ Run the Tests
#### Using Maven
```bash
./gradlew test
```
#### Using TestNG in VSCode
1. Install the **TestNG** extension.
2. Right-click `TestCases.java` → Select `Run Test`.

---

## 📊 Test Cases
### ✅ Test Case 1: Scrape Hockey Team Data
- Visits `Hockey Teams` page.
- Extracts team names, win percentages & year for teams with `Win% < 40%`.
- Saves as `hockey-team-data.json`.

### ✅ Test Case 2: Scrape Oscar Winning Films
- Visits `Oscar Winning Films` page.
- Extracts **top 5 movies per year** based on award count.
- Saves as `oscar-winner-data.json`.

---

## 📁 Output Files
- **hockey-team-data.json**: Extracted hockey teams data.
- **oscar-winner-data.json**: Extracted Oscar-winning films.

---

## 🏆 Features
✔ Automated web scraping with Selenium.  
✔ Efficient JSON storage with Jackson.  
✔ Structured test execution using TestNG.  
✔ Optimized XPath selection for fast data extraction.  
✔ Log capturing via `ChromeDriverService`.  

---

## 📜 License
This project is free to use with a follow.

---

## 🤝 Contributing
Contributions are welcome! Feel free to open a pull request.  
📩 Contact: [Gourab Pal](mailto:gourab.pal.gpal@gmail.com)  
🔗 LinkedIn: [Gourab Pal](http://www.linkedin.com/in/gourab-pal-0327801a4)

---

## ⭐ Acknowledgments
Special thanks to:
- **Selenium WebDriver** for automation.
- **TestNG** for test execution.
- **Jackson** for JSON processing.

🔹 _Happy Coding!_ 🚀

