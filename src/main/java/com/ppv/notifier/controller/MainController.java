package com.ppv.notifier.controller;

import com.ppv.notifier.entity.User;
import com.ppv.notifier.model.NotificationModel;
import com.ppv.notifier.service.MessageService;
import com.ppv.notifier.service.NotificationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main controller.
 *
 * @author Pavlo.Pavlichenko
 */
@Controller
@RequestMapping("/")
public class MainController {
	private final MessageService messageService;
	private final NotificationService notificationService;

	public MainController(MessageService messageService, NotificationService notificationService) {
		this.messageService = messageService;
		this.notificationService = notificationService;
	}

	@GetMapping
	public String main(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("profile", user);

		if (user == null) {
			return "index";
		}
		
		return "redirect:messages";
	}

	@GetMapping("/messages")
	public String messages(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("profile", user);
		model.addAttribute("notification", new NotificationModel());
		model.addAttribute("messages", messageService
				.findMessagesByAuthorId(user.getId()));

		return "messages";
	}

	@PostMapping("/messages")
	public String sendMessage(@ModelAttribute NotificationModel notification, @AuthenticationPrincipal User user) {
		messageService.save(notification, user);
		notificationService.send(notification);

		return "redirect:messages";
	}
}
