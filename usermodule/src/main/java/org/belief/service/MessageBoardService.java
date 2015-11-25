package org.belief.service;

import java.util.List;

public interface MessageBoardService {

	public List<org.belief.domain.Message> listMessages();
	public void postMessage(org.belief.domain.Message message);
	public void deleteMessage(org.belief.domain.Message message);
	public org.belief.domain.Message findMessageById(Long messageId);
}
