package com.example.usm.tickets_project.converter;

import com.example.usm.tickets_project.model.Block;
import com.example.usm.tickets_project.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class

BlockConverter {
    private final BlockRepository blockRepository;
    @Autowired
    public BlockConverter(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public Long fromBlockToLongBlockId(Block block){
        return block.getBlockId();
    }

    public Block fromLongBlockIdToBlock(Long id){
        return blockRepository.findById(id);
    }
}
