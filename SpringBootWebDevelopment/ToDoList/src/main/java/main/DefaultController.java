package main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class DefaultController {

    private final FileService fileService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("filesDescriptors", fileService.listFileDescriptors());
        model.addAttribute("filesCount", fileService.listFileDescriptors().size());

        return "index";
    }
}
