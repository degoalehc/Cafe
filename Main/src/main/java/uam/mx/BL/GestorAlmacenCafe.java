package uam.mx.bl;

import java.util.ArrayList;
import java.util.List;

import uam.mx.bl.dto.CafeDto;
import uam.mx.dal.CafeDao;
import uam.mx.dal.entities.Cafe;

public class GestorAlmacenCafe {
    private CafeDao cafeDao = new CafeDao();

    public CafeDto agregarCafe(Cafe cafe) {
        try {
            Cafe savedCafe = cafeDao.save(cafe);

            if (savedCafe != null) {
                CafeDto cafeDto = new CafeDto();
                cafeDto.setId(savedCafe.getId());
                cafeDto.setNombre(savedCafe.getNombre());
                cafeDto.setLocacion(savedCafe.getLocacion());
                cafeDto.setCantidad(savedCafe.getCantidad());
                
                
                System.out.println("Café agregado al almacén: " + cafeDto);
                return cafeDto;
            } else {
                System.out.println("Error: No se pudo guardar el café.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al agregar café: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean eliminarCafe(int id) {
        try {
            boolean deleted = cafeDao.delete(id);
            if (deleted) {
                System.out.println("Café eliminado con ID: " + id);
            } else {
                System.out.println("Error: No se pudo eliminar el café con ID: " + id);
            }
            return deleted;
        } catch (Exception e) {
            System.err.println("Error al eliminar café: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<CafeDto> obtenerTodosLosCafes() {
        try {
            List<Cafe> cafes = cafeDao.getAll();

            if (cafes == null || cafes.isEmpty()) {
                System.out.println("No hay cafés en el almacén.");
                return new ArrayList<>(); 
            }

            List<CafeDto> cafesDto = new ArrayList<>();
            for (Cafe cafe : cafes) {
                CafeDto cafeDto = new CafeDto();
                cafeDto.setId(cafe.getId());
                cafeDto.setNombre(cafe.getNombre());
                cafeDto.setLocacion(cafe.getLocacion());
                cafeDto.setCantidad(cafe.getCantidad());
                
                cafesDto.add(cafeDto);
            }

            System.out.println("Cafés obtenidos: " + cafesDto.size());
            return cafesDto;
        } catch (Exception e) {
            System.err.println("Error al obtener cafés: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }

}