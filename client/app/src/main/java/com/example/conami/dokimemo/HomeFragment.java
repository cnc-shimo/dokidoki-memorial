package com.example.conami.dokimemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static android.R.attr.animation;
import static android.R.attr.value;
import static android.R.attr.width;

public class HomeFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";

    View rootView;
    ImageView status;
    ViewGroup.LayoutParams bombLayoutParams;
    ViewGroup.MarginLayoutParams bombMarginLayoutParams;
    TextView measure;
    ViewGroup.MarginLayoutParams measureMarginLayoutParams;
    int maxAnimationNumber =10; // 後で消す.

    int[] scales = {
            R.mipmap.ic_mouse,
            R.mipmap.ic_cat2,
            R.mipmap.ic_wolf,
            R.mipmap.ic_lion,
            R.mipmap.ic_elephant,
            R.mipmap.ic_whale,

    };

    int[] statuses = {
            R.drawable.ic_happy_face,
            R.drawable.ic_normal_face,
            R.drawable.ic_sad_face,
            R.drawable.ic_bomb_icon
    };

    ImageView scale;
    ViewGroup.MarginLayoutParams scaleMarginLayoutParams;
    public HomeFragment() {}

    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    int totalValue = -1; // 後で消す.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        status = (ImageView)rootView.findViewById(R.id.image_bomb);

        ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            //rootView描画後の処理
            public void onGlobalLayout() {
                // scale画像の設定.
                // onCreate内でやろうとすると一旦rootViewを秒が描画してからでないとviewのサイズが取れない.

                HttpRequest httpRequest = new HttpRequest();
                httpRequest.setFunc(new FuncInterface() {
                    @Override
                    public void func(TextView textView, String json) {//jsonはGETで取得したjsonの文字列．
                        try {
                            Log.d("json", json);
                            JSONObject jsonObject = new JSONObject(json);
                            totalValue = jsonObject.getInt("total_value");

                            ArrayList<ImageView> scaleList = new ArrayList<ImageView>();
                            scaleList.add((ImageView)rootView.findViewById(R.id.scale1));
                            scaleList.add((ImageView)rootView.findViewById(R.id.scale2));
                            scaleList.add((ImageView)rootView.findViewById(R.id.scale3));
                            scaleList.add((ImageView)rootView.findViewById(R.id.scale4));
                            scaleList.add((ImageView)rootView.findViewById(R.id.scale5));
                            scaleList.add((ImageView)rootView.findViewById(R.id.scale6));

                            //totalValue<0の時にバグるの対策
                            if(totalValue<0) {
                                Log.d("totalValue<0", "" + totalValue);
                                totalValue = 0;
                            }

                            int scaleNum = totalValue/3;
                            if(scaleNum>5) {
                                scale = scaleList.get(5);
                            }else{
                                scale = scaleList.get(scaleNum);
                            }

                            //status の表示変更
                            int numberOfStatus = 2;//totalValue/3;
                            if(numberOfStatus > statuses.length - 2){
                                // ボムの時
                                status.setImageResource(statuses[statuses.length-1]);
                                //ボムとスケールの更新
                                updateBom(rootView.getWidth(), rootView.getHeight());
                                //ボムのアニメーション設定
                                animateBomb(status);
                            }else{
                                // ボム以外の時
                                status.setImageResource(statuses[numberOfStatus]);
                            }
                        } catch (JSONException e) {
                            Log.e("JSONExeption", e.toString());
                        }
                    }
                });
                //String url = "http://210.140.69.130/api/v1/users/"+UserModel.getId()+"/frustrations.json";
                String url = "http://210.140.70.106/api/v1/users/"+UserModel.getId()+"/frustrations.json";
                httpRequest.get(url);

                //リスナーを削除する
                rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        return rootView;
    }


    private void getTotalValue(){
        //サーバーからvalueを取得
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setFunc(new FuncInterface() {
            @Override
            public void func(TextView textView, String json) {//jsonはGETで取得したjsonの文字列．
                try {
                    Log.d("json", json);
                    JSONObject jsonObject = new JSONObject(json);
                    totalValue = jsonObject.getInt("total_value");

                } catch (JSONException e) {
                    Log.e("JSONExeption", e.toString());
                }
            }
        });
        //String url = "http://210.140.69.130/api/v1/users/"+UserModel.getId()+"/frustrations.json";
        String url = "http://210.140.70.106/api/v1/users/"+UserModel.getId()+"/frustrations.json";
        httpRequest.get(url);

        //totalValue<0の時にバグるの対策
        if(totalValue<0) {
            Log.d("totalValue<0", "" + totalValue);
            totalValue = 0;
        }
    }

    private void updateBom(int width, int height) {
        /* ボムサイズの変更
         * bom size is changes "sizeStep" times.
         * bom scale is changes "scaleStep" times.
         * */
        bombLayoutParams = status.getLayoutParams();
        bombMarginLayoutParams = (ViewGroup.MarginLayoutParams)bombLayoutParams;

        int sizeStep = 3;
        int numberOfFrustrations = totalValue;
        Log.d("totalValue", "" + totalValue);
        int bombSize  = (int)numberOfFrustrations % sizeStep; // for bom_image size
        int bombScale = (int)numberOfFrustrations / sizeStep;
        bombMarginLayoutParams.leftMargin  = (int)(width * 0.5 * (0.9 - 0.4 * bombSize)); //画面左端からの長さを指定
        bombMarginLayoutParams.rightMargin = (int)(width * 0.5 * (0.9 - 0.4 * bombSize)); //画面右端からの長さを指定
        status.setLayoutParams(bombMarginLayoutParams);

        ImageView scaleImage;

        // scale の画像設定
        if (bombScale < scales.length) {
            scale.setImageResource(scales[bombScale]);
            scale.setColorFilter(0xfff3a9cd, PorterDuff.Mode.SRC_IN);
        } else {
            scale.setImageResource(scales[scales.length - 1]);
            scale.setColorFilter(0xfff3a9cd,PorterDuff.Mode.SRC_IN);
        }

    }

    AnimatorSet animatorSet;
    boolean canceldBombAnimation = true;
    private void animateBomb(ImageView target) {
        // AnimatorSetに渡すAnimatorのリストです
        List<Animator> animatorList= new ArrayList<Animator>();

        // 倍率を１．０から０．７へ変化
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(target,
                PropertyValuesHolder.ofFloat("scaleX", 0.7f),
                PropertyValuesHolder.ofFloat("scaleY", 0.7f)
        );

        // ０．５秒かけて実行させます.
        scaleDown.setDuration(500);

        // 倍率を０．７から１．５へ変化.
        ObjectAnimator scaleUp = ObjectAnimator.ofPropertyValuesHolder(target,
                PropertyValuesHolder.ofFloat("scaleX", 1.5f),
                PropertyValuesHolder.ofFloat("scaleY", 1.5f),
                PropertyValuesHolder.ofFloat("alpha",  0.7f)
        );

        // ０．５秒かけて実行させます.
        scaleUp.setDuration(500);

        // 倍率を１．５から１．０へ変化.
        ObjectAnimator scaleNormal = ObjectAnimator.ofPropertyValuesHolder(target,
                PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 1.0f),
                PropertyValuesHolder.ofFloat("alpha",  1.0f)
        );

        // ０．５秒かけて実行させます.
        scaleNormal.setDuration(500);

        if (animatorSet!=null) {
            animatorSet.cancel();
        }

        animatorSet = new AnimatorSet();
        animatorList.add(scaleDown);
        animatorList.add(scaleUp);
        animatorList.add(scaleNormal);

        // リストのAnimatorを順番に実行します.
        animatorSet.playSequentially(animatorList);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                canceldBombAnimation = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(canceldBombAnimation) {
                }else{
                    animation.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                canceldBombAnimation = true;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        // アニメーションを開始します
        animatorSet.start();
    }
}
