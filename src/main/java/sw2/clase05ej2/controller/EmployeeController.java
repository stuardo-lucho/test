package sw2.clase05ej2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase05ej2.entity.Employee;
import sw2.clase05ej2.repository.EmployeeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = {"", "/", "list"})
    public String listarEmpleados(Model model) {
        model.addAttribute("listaEmpleados", employeeRepository.findAll());
        model.addAttribute("listaEmpleadosPorRegion", employeeRepository.obtenerEmpleadosPorRegion());
        model.addAttribute("listaEmpleadosPorPais", employeeRepository.obtenerEmpleadosPorPais());
        return "employee/list";
    }

    @GetMapping("/new")
    public String nuevoEmpleadoFrm(Model model) {
        model.addAttribute("listaJefes", getListaJefes());
        return "employee/newFrm";
    }

    public List<Employee> getListaJefes() {
        List<Employee> listaJefes = employeeRepository.findAll();
        Employee e = new Employee();
        e.setId(0);
        e.setFirstname("--No tiene Jefe--");
        listaJefes.add(0, e);
        return listaJefes;
    }

    @PostMapping("/save")
    public String guardarEmpleado(Employee employee,
                                  @RequestParam("birthdateStr") String birthdateStr,
                                  @RequestParam("hiredateStr") String hiredateStr) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            employee.setBirthdate(formatter.parse(birthdateStr));
            employee.setHiredate(formatter.parse(hiredateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model, @RequestParam("id") int id) {
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("employee", optional.get());
            model.addAttribute("listaJefes", getListaJefes());
            return "employee/editFrm";
        } else {
            return "redirect:/employee";
        }

    }

    @GetMapping("/delete")
    public String borrarEmpleado(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
        }
        attr.addFlashAttribute("msg","usuario borrado exitosamente");
        return "redirect:/employee";
    }
}
