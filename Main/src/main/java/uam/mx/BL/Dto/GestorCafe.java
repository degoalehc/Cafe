package uam.mx.BL.Dto;

import uam.mx.Dal.CafeDao;
import uam.mx.Dal.entities.Cafe;

public class GestorCafe {
    CafeDao cafeteria = new CafeDao();
    
    public CafeDto agregarCafe(Cafe cafe){

        return new CafeDto();
    }

}
