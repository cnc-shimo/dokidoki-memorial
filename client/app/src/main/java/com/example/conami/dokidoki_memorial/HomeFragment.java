package com.example.conami.dokidoki_memorial;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.animation;

/**
 * Created by matsushita on 2016/11/08.
 */

public class HomeFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    View rootView;
    ImageView bomb;
    ViewGroup.LayoutParams bombLayoutParams;
    ViewGroup.MarginLayoutParams bombMarginLayoutParams;
    TextView measure;
    ViewGroup.MarginLayoutParams measureMarginLayoutParams;
    int maxAnimationNumber =10; //後で消す．
    int[] scales = {
            R.drawable.ic_atom,
            R.drawable.ic_microorganism,
            R.drawable.ic_planaria,
            R.drawable.ic_mouse,
            R.drawable.ic_cat,
            R.drawable.ic_car,
            R.drawable.ic_whale,
            R.drawable.ic_airplane,
            R.drawable.ic_mt_fuji,
            R.drawable.ic_japan,
            R.drawable.ic_moon,
            R.drawable.ic_earth,
            R.drawable.ic_galaxy,
            R.drawable.ic_infty
    };

    ImageView scale;
    ViewGroup.MarginLayoutParams scaleMarginLayoutParams;
    public HomeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    int count = -1;//後で消す.
    boolean call = false; //後で消す。
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        bomb = (ImageView) rootView.findViewById(R.id.image_bomb);
        bomb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.i("onClick","HomeFragment#onCreateView");
                updateBom(rootView.getWidth(),rootView.getHeight());
            }
        });

        measure = (TextView) rootView.findViewById(R.id.text_measure);

        ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            //rootView描画後の処理
            public void onGlobalLayout() {
                //なんかここは何回も呼び出されているっぽい.どうにかしたい.
                //scale画像の設定.
                // onCreate内でやろうとすると一旦rootViewを秒が描画してからでないとviewのサイズが取れない.
                int width = rootView.getWidth();
                int height = rootView.getHeight();
                scale = (ImageView) rootView.findViewById(R.id.image_bom_scale);
                scaleMarginLayoutParams = (ViewGroup.MarginLayoutParams) scale.getLayoutParams();
                scaleMarginLayoutParams.width  = (int) (0.1 * rootView.getWidth());
                scaleMarginLayoutParams.height = (int) (0.1 * rootView.getWidth());
                scaleMarginLayoutParams.rightMargin = (int) (0.1 * rootView.getWidth());
                scale.setLayoutParams(scaleMarginLayoutParams);

                measureMarginLayoutParams = (ViewGroup.MarginLayoutParams) measure.getLayoutParams();
                measureMarginLayoutParams.width  = (int) (0.1* rootView.getWidth());
                measureMarginLayoutParams.height = (int) (0.1* rootView.getWidth());

                //最初のボムの更新
                //updateBom(rootView.getWidth(),rootView.getHeight());

            }
        });

        if(call) {
        }else{
            animateBomb(bomb);
        }
        return rootView;
    }

    private void updateBom(int width, int height){
        /* ボムサイズの変更
         * bom size is changes "sizeStep" times.
         * bom scale is changes "scaleStep" times.
         * */
        bombLayoutParams = bomb.getLayoutParams();
        bombMarginLayoutParams =
                (ViewGroup.MarginLayoutParams)bombLayoutParams;


        count+=1; //後で消す
        int sizeStep = 3;
        int numberOfFrustrations = count; //本来はサーバから取得
        Log.d("count",""+count);
        int bombSize  = (int) numberOfFrustrations % sizeStep; // for bom_image size
        int bombScale = (int) numberOfFrustrations / sizeStep;
        bombMarginLayoutParams.leftMargin  = (int) (width * 0.5 * (0.9 - 0.4 * bombSize));  //画面左端からの長さを指定
        bombMarginLayoutParams.rightMargin = (int) (width * 0.5 * (0.9 - 0.4 * bombSize));  //画面右端からの長さを指定
        bomb.setLayoutParams(bombMarginLayoutParams);

        //scale の画像設定
        if(bombScale < scales.length){
            scale.setImageResource(scales[bombScale]);
        }else{
            scale.setImageResource(scales[scales.length - 1]);
        }
        measure.setText("10^"+bombScale);
    }

    AnimatorSet animatorSet;
    private void animateBomb( ImageView target ) {
        // AnimatorSetに渡すAnimatorのリストです
        List<Animator> animatorList= new ArrayList<Animator>();

        // 倍率を１．０から０．７へ変化
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(target,
                PropertyValuesHolder.ofFloat("scaleX", 0.7f),
                PropertyValuesHolder.ofFloat("scaleY", 0.7f));
        // ０．５秒かけて実行させます
        scaleDown.setDuration(500);

        // 倍率を０．５から１．５へ変化
        ObjectAnimator scaleUp = ObjectAnimator.ofPropertyValuesHolder(target,
                PropertyValuesHolder.ofFloat("scaleX", 1.5f),
                PropertyValuesHolder.ofFloat("scaleY", 1.5f),
                PropertyValuesHolder.ofFloat("alpha",  0.7f));
        // ０．５秒かけて実行させます
        scaleUp.setDuration(500);

        // 倍率を１．５から１．０へ変化
        ObjectAnimator scaleNormal = ObjectAnimator.ofPropertyValuesHolder(target,
                PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 1.0f),
                PropertyValuesHolder.ofFloat("alpha",  1.0f));
        // ０．５秒かけて実行させます
        scaleNormal.setDuration(500);

        if(animatorSet!=null){
            animatorSet.cancel();
        }
        animatorSet = new AnimatorSet();
        animatorList.add(scaleDown);
        animatorList.add(scaleUp);
        animatorList.add(scaleNormal);

        // リストのAnimatorを順番に実行します
        animatorSet.playSequentially( animatorList );

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(maxAnimationNumber > 0) {
                    animatorSet.start();
                    maxAnimationNumber-=1;
                }else{
                    maxAnimationNumber = 10;
                    animatorSet.end();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                animatorSet.end();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        // アニメーションを開始します
        animatorSet.start();

    }
}
