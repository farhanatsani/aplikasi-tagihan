package id.ac.tazkia.payment.virtualaccount.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Data
public class Pembayaran {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    private LocalDateTime waktuTransaksi;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JenisPembayaran jenisPembayaran;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tagihan")
    private Tagihan tagihan;

    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;
    
    @ManyToOne
    @JoinColumn(name = "id_virtual_account")
    private VirtualAccount virtualAccount;

    @NotNull @Min(1)
    private BigDecimal jumlah;

    @NotNull @NotEmpty
    private String referensi;
    private String keterangan;
    
}
