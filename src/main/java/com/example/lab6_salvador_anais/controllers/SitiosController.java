package com.example.lab6_salvador_anais.controllers;
import com.example.lab6_salvador_anais.entity.Location;
import com.example.lab6_salvador_anais.entity.Site;
import com.example.lab6_salvador_anais.entity.Technician;
import com.example.lab6_salvador_anais.repository.LocationRepository;
import com.example.lab6_salvador_anais.repository.SiteRepository;
import com.example.lab6_salvador_anais.repository.TechnicianRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sitios")
public class SitiosController {
    final SiteRepository siteRepository;
    private final TechnicianRepository technicianRepository;
    private final LocationRepository locationRepository;

    public SitiosController(SiteRepository siteRepository,
                            TechnicianRepository technicianRepository,
                            LocationRepository locationRepository) {
        this.siteRepository = siteRepository;
        this.technicianRepository = technicianRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping(value = {"/list", ""})
    public String listarSitios(Model model) {
        List<Technician> list2 = technicianRepository.findAll();
        List<Site> list = siteRepository.findAll();
        List<Location> list3 = locationRepository.findAll();
        model.addAttribute("sitiosList", list);
        model.addAttribute("tecList", list2);
        model.addAttribute("locationList", list3);
        return "vista1/sitios";
    }

    @GetMapping("/edit")
    public String editarSitio(Model model,
                                      @RequestParam("id") int id) {

        Optional<Site> optSite = siteRepository.findById(id);
        Optional<Location> optionalLocation = locationRepository.findById(id);
        Optional<Technician> optionalTechnician = technicianRepository.findById(id);
        if (optSite.isPresent() && optionalLocation.isPresent() && optionalTechnician.isPresent()) {
            Site site = optSite.get();
            Location location = optionalLocation.get();
            Technician technician = optionalTechnician.get();
            model.addAttribute("site", site);
            model.addAttribute("location", location);
            model.addAttribute("technician", technician);
            return "vista1/editarsitio";
        } else {
            return "redirect:/vista1/sitios";
        }
    }
    @PostMapping("/save")
    public String guardarEditSite(Location location, RedirectAttributes attr) {
        String estado = location.getId() == null ? "creado" : "actualizado";
        attr.addFlashAttribute("msg", "Sito " + estado + " correctamente");
        locationRepository.save(location);
        return "redirect:/vista1/sitio";
    }

    @GetMapping("/delete")
    public String borrarSitio(@RequestParam("id") int id, RedirectAttributes attributes) {

        Optional<Location> optionalLocation = locationRepository.findById(id);

        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            String locationCity = location.getCity();
            locationRepository.deleteById(id);
            attributes.addFlashAttribute("msg", "Sitio " + locationCity + " borrado exitosamente");
        }
        return "redirect:/vista1/sitios";

    }


}
