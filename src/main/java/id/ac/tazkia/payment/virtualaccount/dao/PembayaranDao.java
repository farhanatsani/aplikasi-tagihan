package id.ac.tazkia.payment.virtualaccount.dao;

import id.ac.tazkia.payment.virtualaccount.entity.JenisTagihan;
import id.ac.tazkia.payment.virtualaccount.entity.Pembayaran;
import id.ac.tazkia.payment.virtualaccount.entity.Tagihan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PembayaranDao extends PagingAndSortingRepository<Pembayaran, String> {
    Page<Pembayaran> findByTagihanOrderByWaktuTransaksi(Tagihan tagihan, Pageable pageable);

    @Query("select p from Pembayaran  p where p.tagihan.jenisTagihan = :jenis " +
            "and p.waktuTransaksi >= :mulai and p.waktuTransaksi <= :sampai " +
            "order by p.waktuTransaksi desc")
    Page<Pembayaran> findByJenisTagihanAndWaktuTransaksi(
            @Param("jenis")JenisTagihan jenis,
            @Param("mulai")Date mulai,
            @Param("sampai")Date sampai,
            Pageable page
    );

    @Query("select p from Pembayaran  p where " +
            "p.waktuTransaksi >= :mulai and p.waktuTransaksi <= :sampai " +
            "order by p.waktuTransaksi desc")
    Page<Pembayaran> findByWaktuTransaksi(@Param("mulai")Date mulai,
                                          @Param("sampai")Date sampai,
                                          Pageable pageable);

    Page<Pembayaran> findByTagihanJenisTagihan(JenisTagihan jenisTagihan, Pageable pageable);
}
