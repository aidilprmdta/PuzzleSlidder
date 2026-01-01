
## 🚀 Setup & Menjalankan Project

Project ini menggunakan **JavaFX**, sehingga perlu setup tambahan selain JDK.

---

## 🔧 Persiapan Awal

### 1. Install JDK
- Gunakan **JDK 11 atau lebih baru** (disarankan JDK 17 / 21).
- Download dari:
  - https://adoptium.net

Pastikan JDK sudah ter-install dengan cek:
```bash
java --version
````

---

### 2. Download JavaFX SDK

* Download JavaFX SDK sesuai OS:

  * [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
* Ekstrak, misalnya ke:

  ```
  C:\javafx\
  ```

---

## 🧠 Setup di IntelliJ IDEA

### 1. Buka Project

* `File → Open`
* Pilih folder `PuzzleSlidder`

### 2. Set JDK

* `File → Project Structure`
* **Project SDK** → pilih JDK yang sudah di-install
* **Project language level** → sesuai versi JDK

### 3. Set JavaFX Library

* Masuk ke `Project Structure → Libraries`
* Klik `+ → Java`
* Arahkan ke:

  ```
  C:\javafx\lib
  ```

### 4. Konfigurasi Run

* `Run → Edit Configurations`
* Pilih konfigurasi `Application`
* Pada **VM options**, isi:

  ```
  --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml,javafx.media
  ```
* Atau yang mau lebih komplleks

  ```
  --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml,javafx.media  --add-exports=javafx.base/com.sun.javafx=ALL-UNNAMED --add-exports=javafx.graphics/com.sun.glass.utils=ALL-UNNAMED
  ```
* **Main class**: `main.Main` (atau sesuai entry point project)

### 5. Run Project

* Klik ▶️ Run
* Game akan tampil jika konfigurasi benar

---

## 🧠 Setup di Visual Studio Code

### 1. Install Extension

Install extension berikut di VS Code:

* **Extension Pack for Java**
* **JavaFX Support** (opsional tapi membantu)

---

### 2. Set JAVA_HOME

Pastikan environment variable sudah benar:

```bash
JAVA_HOME = C:\Program Files\Eclipse Adoptium\jdk-xx
```

---

### 3. Konfigurasi JavaFX

Buat file `.vscode/launch.json` di project:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Run PuzzleSlidder",
      "request": "launch",
      "mainClass": "main.Main",
      "vmArgs": "--module-path \"C:/javafx/lib\" --add-modules javafx.controls,javafx.fxml,javafx.media"
    }
  ]
}
```

⚠️ Pastikan path JavaFX sesuai lokasi di komputer masing-masing.

---

### 4. Run Project

* Buka file `Main.java`
* Klik ▶️ Run atau tekan `F5`

---

## ❗ Troubleshooting Umum

**Error: JavaFX runtime components are missing**

* Pastikan `--module-path` dan `--add-modules` sudah benar.

**UI tidak muncul**

* Cek entry point (`Main.java`)
* Pastikan `launch()` JavaFX terpanggil

**Audio tidak jalan**

* Pastikan file audio ada di folder `resources/`
* Cek path resource di kode

---

## ✅ Catatan Penting

* Project **tidak bisa dijalankan tanpa JavaFX SDK**
* Pastikan versi JavaFX **sesuai dengan versi JDK**
* Gunakan path absolut jika terjadi error
