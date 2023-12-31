package com.example.traveler.service;

import com.example.traveler.config.BaseException;
import com.example.traveler.model.dto.ItemNameRequest;
import com.example.traveler.model.dto.ItemResponse;
import com.example.traveler.model.entity.ChecklistEntity;
import com.example.traveler.model.entity.User;
import com.example.traveler.repository.ChecklistRepository;
import lombok.AllArgsConstructor;
import com.example.traveler.model.entity.ItemEntity;
import com.example.traveler.model.dto.ItemRequest;
import com.example.traveler.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.traveler.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ChecklistRepository checklistRepository;
    @Autowired
    private UserService userService;

        // 새로운 아이템 정보 저장
        @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
        public ItemResponse saveItem(String accessToken, int cId) throws BaseException {
        User user = userService.getUserByToken(accessToken);
        if (user == null) {
            throw new BaseException(INVALID_JWT);
        }
        // Fetch the ChecklistEntity using cId
        ChecklistEntity checklist = checklistRepository.findById(cId)
                .orElseThrow(() -> new BaseException(ITEM_NOT_FOUND));

        // Create a new ItemEntity based on the itemRequest
        ItemEntity newItem = new ItemEntity();
        newItem.setName("새로운 준비물");
        newItem.setIschecked(false);
        newItem.setChecklist(checklist);

        // Save the new item in the database
        newItem = itemRepository.save(newItem);

        // Create and return the ItemResponse
        return new ItemResponse(newItem.getId(), newItem.getName(), newItem.getIschecked() , newItem.getChecklist().getCId());
    }

    // 모든 item 정보 조회
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public List<ItemResponse> getAllItem() {
        List<ItemEntity> items = itemRepository.findAll();
        return mapItemEntityListToItemResponseList(items);
    }

    // 특정 아이템 정보 조회
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public ItemResponse getItem(int iId) throws BaseException {
        ItemEntity item = (ItemEntity) itemRepository.findById(iId)
                .orElseThrow(() -> new BaseException(ITEM_NOT_FOUND));

        return new ItemResponse (item.getId(), item.getName(), item.getIschecked());
    }

    // 특정 checklist에 포함된 모든 item 정보조회
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public List<ItemResponse> getallItemByChecklist(int cId) throws BaseException {
        ChecklistEntity checklist = checklistRepository.findById(cId)
                .orElseThrow(() -> new BaseException(CHECKLIST_IS_EMPTY));

        List<ItemEntity> items = itemRepository.findAllByChecklist(checklist);
        return mapItemEntityListToItemResponseList(items);
    }

    // ItemEntity 리스트를 ItemResponse 리스트로 변환하는 메서드
    private List<ItemResponse> mapItemEntityListToItemResponseList(List<ItemEntity> items) {
        List<ItemResponse> itemResponses = new ArrayList<>();
        for (ItemEntity item : items) {
            ItemResponse itemResponse = new ItemResponse(item.getId(), item.getName(), item.getIschecked());
            itemResponses.add(itemResponse);
        }
        return itemResponses;
    }


    // 특정 checklist에 포함된 item 수정

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public ItemResponse patchItem(String accessToken, int cId, int iId) throws BaseException {
        User user = userService.getUserByToken(accessToken);
        if (user == null) {
            throw new BaseException(INVALID_JWT);
        }
        ItemEntity item = itemRepository.findByIdAndChecklist_cId(iId, cId)
                .orElseThrow(() -> new BaseException(ITEM_NOT_FOUND));
        if (item.getIschecked()) {
            item.setIschecked(false);
        }else {
            item.setIschecked(true);
        }

        try {
            item = itemRepository.save(item);
        } catch (Exception e) {
            throw new BaseException(SAVE_ITEM_FAIL);
        }

        return new ItemResponse(item.getId(), item.getName(), item.getIschecked());
    }

    public ItemResponse patchItemName(String accessToken, int cId, int iId, ItemNameRequest itemNameRequest) throws BaseException {
        User user = userService.getUserByToken(accessToken);
        if (user == null) {
            throw new BaseException(INVALID_JWT);
        }
        ItemEntity item = itemRepository.findByIdAndChecklist_cId(iId, cId)
                .orElseThrow(() -> new BaseException(ITEM_NOT_FOUND));
        item.setName(itemNameRequest.getName());

        try {
            item = itemRepository.save(item);
        } catch (Exception e) {
            throw new BaseException(SAVE_ITEM_FAIL);
        }

        return new ItemResponse(item.getId(), item.getName(), item.getIschecked());
    }

    // 특정 checklist내 포함된 item 삭제
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public int deleteItem(String accessToken, int cId, int iId) throws BaseException {
        User user = userService.getUserByToken(accessToken);
        if (user == null) {
            throw new BaseException(INVALID_JWT);
        }
        ItemEntity item = (ItemEntity) itemRepository.findByIdAndChecklist_cId(iId, cId)
                .orElseThrow(() -> new BaseException(ITEM_NOT_FOUND));

        try {
            itemRepository.delete(item);
        } catch (Exception e) {
            throw new BaseException(DELETE_ITEM_FAIL);
        }
        return cId;
    }

    // 특정 체크리스트에 포함된 아이템 조회 및 삭제
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public ItemResponse getItemFromChecklist(int cId, int iId) throws BaseException {
        ChecklistEntity checklist = checklistRepository.findById(cId)
                .orElseThrow(() -> new BaseException(CHECKLIST_IS_EMPTY));

        ItemEntity item = itemRepository.findByIdAndChecklist(iId, checklist)
                .orElseThrow(() -> new BaseException(ITEM_NOT_FOUND));

        try {
            itemRepository.delete(item); // 아이템 삭제 시도
        } catch (Exception e) {
            throw new BaseException(DELETE_ITEM_FAIL);
        }

        return new ItemResponse(item.getId(), item.getName(), item.getIschecked());
    }


}