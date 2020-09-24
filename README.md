# ALPR INDONESIA

Aplikasi Rekognisi Plat Nomor Secara Otomatis Menggunakan Teknik Optical Character Recognition. Aplikasi ini memanfaat library ML kit milik google.

Anda bisa menggunakan contoh gambar dibawah ini untuk melakuka test.

![alt text]( https://github.com/thatxxuc/ALPR-ID-OCR/blob/master/contoh%20plat%20id.jpg )

##Cara Menggunakan
```
    1. Buka Aplikasi
    2. Klik Tombol Scan
    3. Arahkan Kamera pada plat yang ingin di rekognisi
    4. Tunggu sampai menampilkan hasil
    5. Selesai
```

## Demo

Demo Penggunaan Aplikasi.

![alt text](  https://github.com/thatxxuc/ALPR-ID-OCR/blob/master/demo.gif )

## Build & Run

```
    1. Clone Repository
    2. Open with Android Studio
    3. Configure Android SDK
    4. Launch application
```

## Customisation

Jika ingin menambahkan format plat yang baru, Anda bisa membuat filter baru dibawah  `com.uci.ta.licenseplatereader.utils.ocr.filters`. 

Contoh


```
//Adding the license plate filters
private LicensePlateManager(){
        filters= new ArrayList<>();
        filters.add(new MalaysiaPlateFilter());
        filters.add(new IDLicensePlateFilter());
    }
```

