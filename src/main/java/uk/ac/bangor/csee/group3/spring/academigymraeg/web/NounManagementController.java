package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uk.ac.bangor.csee.group3.spring.academigymraeg.RepositoryBasedNounImpl;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Noun;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.NounRepository;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_POWER" })
public class NounManagementController {

	@Autowired
	private NounRepository repository;

	@Autowired
	private RepositoryBasedNounImpl nounDetails;

	@RequestMapping("/nouns")
	public String showNounList(Model model) {
		List<Noun> listNouns = (List<Noun>) repository.findAll();
		model.addAttribute("listNouns", listNouns);

		return "nouns";
	}

	@RequestMapping("/createNoun")
	public String showEditNoun(Model model) {
		model.addAttribute("noun", new Noun());
		return "createNoun";
	}

	@PostMapping("/deleteNoun")
	public String deleteNoun(@RequestParam String id) {
		repository.deleteById(id);
		return "redirect:/nouns";
	}

	@RequestMapping("/editNoun")
	public String editNoun(String id, Model model) {
		Noun noun = (Noun) nounDetails.loadNounById(id);
		model.addAttribute("noun", noun);
		return "editNoun";
	}

	@RequestMapping(value = "/saveNoun", method = RequestMethod.POST, params = "action=update")
	public String updateNoun(@ModelAttribute("noun") @Valid Noun noun, BindingResult result, Model model) {
		if (result.hasErrors())
			return showEditNoun(model);

		repository.updateNoun(noun.getCyNoun(), noun.getEnNoun(), noun.getCyGender(), noun.getId());
		return showNounList(model);
	}

	@RequestMapping(value = "/saveNoun", method = RequestMethod.POST, params = "action=create")
	public String createNoun(@ModelAttribute("noun") @Valid Noun noun, BindingResult result, Model model) {
		if (result.hasErrors())
			return showEditNoun(model);
		repository.save(noun);
		return showNounList(model);
	}
}
