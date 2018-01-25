package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.Reply;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.PostAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by 정인섭 on 2018-01-25.
 */

public class ReplyInputViewHolder extends PostViewHolder {
    @BindView(R.id.edit_reply_input)
    EditText edit_reply_input;
    @BindView(R.id.btn_reply_input)
    Button btn_reply_input;
    public ReplyInputViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Content data) {

        btn_reply_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!"".equals(edit_reply_input.getText().toString())){

                    replyButtonClickListener.setOnReplyClick(edit_reply_input.getText().toString());

                }
            }
        });

    }
}
