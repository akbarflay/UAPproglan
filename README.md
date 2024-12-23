# NutriBalance

NutriBalance adalah aplikasi berbasis Java Swing untuk membantu pengguna dalam menjaga kesehatan mereka. Aplikasi ini memiliki antarmuka grafis yang intuitif dan mendukung berbagai fitur, seperti login, konsultasi dengan dokter spesialis, kalkulasi nutrisi, dan lainnya.

---

## **Fitur Utama**
1. **Login**  
   Pengguna dapat memasukkan username dan password untuk mengakses fitur utama aplikasi.

2. **Halaman Utama**  
   Menu utama yang memungkinkan pengguna mengakses:
   - Konsultasi dengan dokter spesialis.
   - Kalkulasi nutrisi makanan.
   - Live chat.
   - Hasil laboratorium.

3. **Konsultasi Dokter Spesialis**  
   Menampilkan daftar dokter spesialis dengan informasi tentang nama, bidang spesialisasi, dan rating.

4. **Kalkulasi Nutrisi**  
   Pengguna dapat memilih makanan, memasukkan beratnya, dan mendapatkan estimasi jumlah kalori, protein, lemak, dan karbohidrat.

5. **Live Chat (Placeholder)**  
   Fitur live chat untuk membantu pengguna bertanya langsung ke ahli kesehatan (belum sepenuhnya diimplementasikan).


---

## **Struktur Proyek**

- **Main Class**: `ModernHealthAppSwing`
- **Library yang Digunakan**:
  - `javax.swing`: Untuk membangun GUI.
  - `java.awt`: Untuk tata letak dan manajemen elemen visual.

---

## **Cara Menjalankan Aplikasi**

1. **Persyaratan**:
   - Java Development Kit (JDK) versi 8 atau lebih baru harus terinstal.

2. **Langkah-Langkah**:
   - Clone atau unduh kode sumber proyek ini.
   - Kompilasi kode menggunakan perintah berikut di terminal:
     ```
     javac -d . ModernHealthAppSwing.java
     ```
   - Jalankan aplikasi dengan perintah berikut:
     ```
     java main.nutribalance.ModernHealthAppSwing
     ```

---

## **Pengembangan dan Perluasan**

### Fitur yang Akan Ditambahkan:
- Implementasi penuh fitur **Live Chat** untuk interaksi real-time.
- Sistem manajemen hasil laboratorium.
- Penambahan data makanan lebih lengkap untuk kalkulasi nutrisi.

### Kontribusi:
Jika Anda ingin berkontribusi pada pengembangan NutriBalance:
1. Fork repositori ini.
2. Lakukan perubahan pada branch baru.
3. Ajukan pull request dengan deskripsi perubahan yang jelas.

---

## **Tampilan Aplikasi**

### Halaman Login:
- Judul: "Welcome to HealthApp".
- Input: Username dan Password.
- Tombol Login.

### Halaman Utama:
- Tombol:
  - Dokter Spesialis
  - Kalkulasi Nutrisi
  - Live Chat


### Halaman Dokter Spesialis:
- Daftar dokter dengan spesialisasi dan rating.
- Tombol kembali ke halaman utama.

### Halaman Kalkulasi Nutrisi:
- Dropdown untuk memilih makanan.
- Input berat makanan.
- Hasil berupa estimasi nutrisi (kalori, protein, lemak, karbohidrat).
