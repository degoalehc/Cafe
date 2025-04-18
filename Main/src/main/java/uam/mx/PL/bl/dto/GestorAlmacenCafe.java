<<<<<<< HEAD:Main/src/main/java/uam/mx/PL/bl/dto/GestorAlmacenCafe.java
package uam.mx.pl.bl.dto;
=======
package uam.mx.PL.BL.Dto;
>>>>>>> 9d70db475441f98ac9697e7277e97052ec42acd9:Main/src/main/java/uam/mx/BL/GestorAlmacenCafe.java

import java.util.ArrayList;
import java.util.List;

import uam.mx.BL.Dto.CafeDto;
import uam.mx.Dal.CafeDao;
import uam.mx.Dal.entities.Cafe;

// Gestor principal para operaciones con cafés
public class GestorAlmacenCafe {
    private CafeDao cafeDao = new CafeDao();

    // Agrega un nuevo café al sistema
    public CafeDto agregarCafe(Cafe cafe) {
        try {
            Cafe savedCafe = cafeDao.save(cafe);
            if (savedCafe != null) {
                CafeDto cafeDto = new CafeDto();
                cafeDto.setId(savedCafe.getId());
                cafeDto.setNombre(savedCafe.getNombre());
                cafeDto.setLocacion(savedCafe.getLocacion());
                cafeDto.setCantidad(savedCafe.getCantidad());
                return cafeDto;
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error al agregar café: " + e.getMessage());
            return null;
        }
    }

    // Elimina un café por su ID
    public boolean eliminarCafe(int id) {
        try {
            return cafeDao.delete(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar café: " + e.getMessage());
            return false;
        }
    }

    // Obtiene todos los cafés registrados
    public List<CafeDto> obtenerTodosLosCafes() {
        try {
            List<Cafe> cafes = cafeDao.getAll();
            List<CafeDto> cafesDto = new ArrayList<>();
            
            for (Cafe cafe : cafes) {
                CafeDto dto = new CafeDto();
                dto.setId(cafe.getId());
                dto.setNombre(cafe.getNombre());
                dto.setLocacion(cafe.getLocacion());
                dto.setCantidad(cafe.getCantidad());
                cafesDto.add(dto);
            }
            return cafesDto;
        } catch (Exception e) {
            System.err.println("Error al obtener cafés: " + e.getMessage());
            return new ArrayList<>();
        }
    }
<<<<<<< HEAD:Main/src/main/java/uam/mx/PL/bl/dto/GestorAlmacenCafe.java
    public Cafe actualizarCafe(CafeDto cafe) {
        try {
            Cafe cafeEntity = new Cafe();
            cafeEntity.setId(cafe.getId());
            cafeEntity.setNombre(cafe.getNombre());
            cafeEntity.setLocacion(cafe.getLocacion());
            cafeEntity.setCantidad(cafe.getCantidad());
            return cafeDao.update(cafeEntity); // Actualizar en la base de datos
        } catch (Exception e) {
            System.err.println("Error al actualizar café: " + e.getMessage());
            return null; // Devuelve null en caso de error
        }
    }
}
=======
public boolean actualizarCafe(CafeDto cafe) {
    try {
        Cafe cafeEntity = new Cafe();
        cafeEntity.setId(cafe.getId());
        cafeEntity.setNombre(cafe.getNombre());
        cafeEntity.setLocacion(cafe.getLocacion());
        cafeEntity.setCantidad(cafe.getCantidad());
        return cafeDao.update(cafeEntity);  // Actualizar en la base de datos
    } catch (Exception e) {
        System.err.println("Error al actualizar café: " + e.getMessage());
        return false;
    }
}
}
>>>>>>> 9d70db475441f98ac9697e7277e97052ec42acd9:Main/src/main/java/uam/mx/BL/GestorAlmacenCafe.java
