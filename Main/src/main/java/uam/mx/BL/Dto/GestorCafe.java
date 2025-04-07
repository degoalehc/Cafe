package uam.mx.bl.dto;

import uam.mx.dal.CafeDao;
import uam.mx.dal.entities.Cafe;

public class GestorCafe {
    CafeDao cafeteria = new CafeDao();
    
    public CafeDto agregarCafe(Cafe cafe){

        return new CafeDto();
    }

}
