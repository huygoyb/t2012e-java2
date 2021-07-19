1. Tạo class có tên Article với các thuộc t ính bao gồm
    - url: đường link của bài viết.
    - title: tiêu đề bài viết.
    - description: mô tả bài viết.
    - content: nội dung bài viết.
    - thumbnail: ảnh đại diện.
    - createdAt: ngày tạo.
    - updatedAt: ngày update.
    - status: trạng thái bài viết. 0 là chưa hoàn thiện, 1 là đã hoàn thiện, -1 là đã xoá.
2. Constructor với đầy đủ các thuộc tính. 
3. Viết hàm main tạo ra đối tượng của lớp Article với constructor trên, 
      thực hiện lưu đối tượng này vào database có tên "daily-news" bảng "articles" 
      (khuyến khích viết một hàm khác trong main)
4. Add jsoup vào pom.xml.
5. Sử dụng jsoup request đến link "https://vnexpress.net/the-thao".
6. Sử dụng selector để lấy danh sách link trong dữ liệu trả về.
  - html (tạo ra bộ khung của trang web), css (style cho trang web), js (tạo ra nội dung động, tương tác với ngưởi dùng)
  - css có selector để gọi đến các phần tử cần làm việc. Có 4 loại cơ bản: universal (*), id (#), class (.), tag (tag name).
mix selector "h1 p.class a"
    