package com.barisd.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ÖNEMLİ!!!
 * Mutlaka tüm modeller serileştirilimelidir.
 * Ayrıca paket ismi dahil bu sunufun aynısı iletilen serviste de olmalıdır.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveAuthModel implements Serializable {
    Long authid;
    String username;
    String email;
}
