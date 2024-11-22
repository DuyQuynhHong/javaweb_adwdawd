//package com.example.web_nhom_5.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Cho phép truy cập từ các nguồn (origins) được chỉ định
//        config.addAllowedOrigin("http://localhost:8081/web-nhom-5/home/**");  // Thay "http://allowed-origin.com" bằng domain của bạn
//        config.addAllowedOrigin("http://another-allowed-origin.com"); // Thêm domain khác nếu cần
//
//        // Cho phép các phương thức HTTP được chỉ định
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//
//        // Cho phép các header cụ thể hoặc tất cả header
//        config.addAllowedHeader("*"); // Dùng "*" để cho phép tất cả header
//
//        // Cho phép gửi cookie và thông tin xác thực
//        config.setAllowCredentials(true);
//
//        // Thiết lập thời gian cache của CORS (đơn vị là giây)
//        config.setMaxAge(3600L); // 3600 giây = 1 giờ
//
//        // Định cấu hình các URL mà các quy tắc CORS sẽ áp dụng
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);  // Áp dụng cho tất cả các URL trong ứng dụng
//
//        // Trả về CorsFilter với cấu hình đã thiết lập
//        return new CorsFilter(source);
//    }
//}
