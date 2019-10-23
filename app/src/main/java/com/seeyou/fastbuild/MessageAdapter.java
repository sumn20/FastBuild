package com.seeyou.fastbuild;

import androidx.annotation.Nullable;

import com.seeyou.toolkit.thirdparty.baserecyclerview.BaseQuickAdapter;
import com.seeyou.toolkit.thirdparty.baserecyclerview.BaseViewHolder;

import java.util.List;

/**
 * @author sumn
 * date 2019/10/22
 */
public class MessageAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {
    public MessageAdapter(@Nullable List<Message> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        helper.setText(R.id.name, item.getNcontent());
    }
}
