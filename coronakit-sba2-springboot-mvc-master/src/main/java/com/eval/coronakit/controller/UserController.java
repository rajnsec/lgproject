package com.eval.coronakit.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CoronaKitService coronaKitService;
	
	@Autowired
	KitDetailService kitDetailService;
	
	Map<Integer, List<String>> finalKit = new HashMap<>();
	int finalTotalQty = 0;
	int finalTotalAmt = 0;
	int coronaKitId=0;

	
	@RequestMapping("/home")
	public String home() {
		return "user-home";
	}
	
	@RequestMapping("/show-kit")
	public String showKit(Model model) {
		return "show-cart";
	}

	@RequestMapping("/show-list")
	public String showList(Model model) {
		model.addAttribute("products",productService.getAllProducts());
		return "show-all-item-user";
		
	}
	
	
	@PostMapping("/add-to-cart")
	public String showKit(@RequestParam("quantity") String quantity, @ModelAttribute("product") ProductMaster product,
			Model model) {
		Map<Integer, List<String>> map = new HashMap<>();
		List<String> prodQtyList = new ArrayList<>();
		String[] arr = quantity.split(",");
		for (String i : arr) {
			if (i.charAt(1) != '0') {
				prodQtyList.add(i);
			}

		}	
		int totalQty = 0;
		int totalamt = 0;
		for (int i = 0; i < prodQtyList.size(); i++) {
			totalQty=totalQty+Character.getNumericValue((prodQtyList.get(i).charAt(1)));
			ProductMaster p = productService.getProductById(Character.getNumericValue((prodQtyList.get(i).charAt(0))));
			List<String> prodList = new ArrayList<>();
			int amount = Character.getNumericValue((prodQtyList.get(i).charAt(1)))*p.getCost();
			totalamt=totalamt+amount;
			prodList.add(p.getProductName());
			prodList.add(String.valueOf(prodQtyList.get(i).charAt(1)));
			prodList.add(String.valueOf(p.getCost()));
			prodList.add(String.valueOf(amount));  
			  map.put(p.getId(), prodList);
		}
		model.addAttribute("map", map);
		model.addAttribute("totalqty", totalQty);
		model.addAttribute("tamnt", totalamt);
		finalKit=map;
		finalTotalAmt=totalamt;
		finalTotalQty=totalQty;
		//checkout(product, model, map);
		return "show-cart";
	}


	
	@RequestMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("coronakit", new CoronaKit());
		return "checkout-address";
	}
	
	@RequestMapping("/finalize")
	public String finalizeOrder(@RequestParam("address") String address,Model model, Principal principal) {
		model.addAttribute("customername",principal.getName());
		model.addAttribute("address",address);
		model.addAttribute("map", finalKit);
		model.addAttribute("totalqty", finalTotalQty);
		model.addAttribute("tamnt",finalTotalAmt );
		
		CoronaKit coronakit=new CoronaKit();
		coronakit.setId(++coronaKitId);
		coronakit.setDeliveryAddress(address);
		coronakit.setOrderDate(LocalDateTime.now().toString());
		coronakit.setTotalAmount(finalTotalAmt);
		coronaKitService.saveKit(coronakit);
		
		Set<Integer> keys = finalKit.keySet();
		for(Integer key: keys) {
			KitDetail kit = new KitDetail();
			kit.setProductId(key);
			kit.setCoronaKitId(coronaKitId);
			kit.setQuantity(Integer.parseInt((finalKit.get(key).get(1))));
			kit.setAmount(Integer.parseInt(finalKit.get(key).get(3)));
			kitDetailService.addKitItem(kit);
		} 
	  	return "show-summary";
	}

	@RequestMapping("/delete")
	public String deleteItem(@RequestParam("pid") int pid,@RequestParam("qty") int qty,ProductMaster product,Model model) {
			
			  ProductMaster p = productService.getProductById(pid); 
			  int amount=p.getCost()*qty;
			  finalTotalAmt=finalTotalAmt-amount;
			  finalTotalQty=finalTotalQty-qty;
			  finalKit.remove(pid); 			 
			  model.addAttribute("map", finalKit); 
			  model.addAttribute("totalqty", finalTotalQty); 
			  model.addAttribute("tamnt",finalTotalAmt);			 
			  return "show-cart";

	}

}
