package hust.project.promotionservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "slug", unique = true)
    private String slug;

    private String description;

    @Column(name = "coupon_code", unique = true)
    private String couponCode;

    private String discountType;

    private String usageType;

    private String applyTo;

    private Long usageLimit;

    private Long usageCount;

    private Long discountPercentage;

    private Long discountAmount;

    private Long minimumOrderAmount;

    private Boolean isActive;

    private Instant startDate;

    private Instant endDate;
}
