package sw2.clase05ej2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase05ej2.entity.Shipper;
import sw2.clase05ej2.repository.ShipperRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    ShipperRepository shipperRepository;

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Shipper> lista = shipperRepository.findAll();
        model.addAttribute("shipperList", lista);

        return "shipper/list";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm(@ModelAttribute("shipper") Shipper shipper) {
        return "shipper/form";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(@ModelAttribute("shipper") Shipper shipper,
                                            RedirectAttributes attr) {
        if (shipper.getShipperId() == 0) {
            attr.addFlashAttribute("msg", "Usuario creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Usuario actualizado exitosamente");
        }
        shipperRepository.save(shipper);
        return "redirect:/shipper/list";
    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("shipper") Shipper shipper,
                                      Model model, @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/form";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            shipperRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Transportista borrado exitosamente");
        }
        return "redirect:/shipper/list";

    }

    @PostMapping("/BuscarTransportistas")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model) {

        List<Shipper> listaTransportistas = shipperRepository.buscarTransPorCompName(searchField);
        model.addAttribute("shipperList", listaTransportistas);

        return "shipper/list";
    }


}

