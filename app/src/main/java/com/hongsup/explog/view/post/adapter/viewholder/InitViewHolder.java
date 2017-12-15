package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.user.source.UserRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-12-12.
 */

public class InitViewHolder extends PostViewHolder {

    @BindView(R.id.textWriter)
    TextView textWriter;
    @BindView(R.id.textSummary)
    TextView textSummary;

    public InitViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Content data) {
        if(checkMyPost){
            // 내 글이면
            textWriter.setText("안녕하세요. "+ UserRepository.getInstance().getUser().getUsername() +" 님!");
            textSummary.setText("당신의 여행 이야기를 작성해보세요");
        }else{
            // 남 글이면
            textWriter.setText("음... 아직 작성이 안되었네요!" );
            textSummary.setText("다음 기회에 여행 이야기를 둘러보세요");
        }

    }
}
