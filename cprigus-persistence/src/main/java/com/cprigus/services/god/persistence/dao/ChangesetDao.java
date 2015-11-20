package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.dto.ConsultaCoro;
import com.cprigus.services.god.persistence.hbm.Changeset;
import com.cprigus.services.god.persistence.hbm.TipoMovimientoEnum;
import java.util.List;

/**
 *
 * @author User
 */
public interface ChangesetDao {
    void guardarChangeset(Changeset changeset);
    List<Changeset> getChangesetUser(String idUser);
    int totalMovemente(TipoMovimientoEnum movement, String idUser);
    List<ConsultaCoro> getConsultaCoro(int days);
}