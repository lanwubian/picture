package com.picture.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云COS客户端配置类
 * 使用@Configuration注解标记为配置类
 * 使用@ConfigurationProperties注解将配置文件中以"cos.client"开头的属性注入到该类中
 * 使用@Data注解自动生成getter、setter等方法
 */
@Configuration
@ConfigurationProperties(prefix = "cos.client")
@Data
public class CosClientConfig {

    /**
     * 域名
     */
    private String host;

    /**
     * secretId
     */
    private String secretId;

    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 区域
     */
    private String region;
    /**
     * 桶名
     */
    private String bucket;

    /**
     * 创建并配置腾讯云COS客户端Bean
     * @return 返回配置好的COSClient实例
     */
    @Bean
    public COSClient cosClient() {
        // 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId,secretKey);
        // 设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 生成cos客户端
        return new COSClient(cred, clientConfig);
    }
}
