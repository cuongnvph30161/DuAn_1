package interfaceservices;

import java.util.List;
import viewmodel.QuanLyBanViewmodel;
import viewmodel.TenBanViewModel;
import viewmodel.defaultViewModel.BanViewModel;

/**
 *
 * @author Doanh
 */
public interface IBanService {

    List<QuanLyBanViewmodel> getListBan();

    boolean insert(BanViewModel vmBan);

    List<TenBanViewModel> getTang1();

    List<TenBanViewModel> getTang2();

    List<TenBanViewModel> getTang3();

    List<TenBanViewModel> getTang4();

    List<TenBanViewModel> getTang5();
}
