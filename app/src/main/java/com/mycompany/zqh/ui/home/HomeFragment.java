package com.mycompany.zqh.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mycompany.zqh.EditActivity;
import com.mycompany.zqh.MyAdapter;
import com.mycompany.zqh.R;
import com.mycompany.zqh.Word;
import com.mycompany.zqh.WordViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FloatingActionButton floatingActionButton;
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    private MyAdapter myAdapter1;
    private LiveData<List<Word>>fillteredwords;
    Toast toast;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            homeViewModel =
                    ViewModelProviders.of(this).get(HomeViewModel.class);
            setHasOptionsMenu(true);
            View root = inflater.inflate(R.layout.fragment_home, container, false);
            wordViewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
            final FloatingActionButton floatingActionButton = root.findViewById(R.id.fab);
            final RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//
            myAdapter1 = new MyAdapter(false,wordViewModel,-1,HomeFragment.this.getContext());
            recyclerView.setAdapter(myAdapter1);
            fillteredwords =wordViewModel.getAllWordsLive();
            fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                @Override
                public void onChanged(List<Word> words) {
                    recyclerView.smoothScrollBy(0,-200);
                    myAdapter1.submitList(words);
                }
            });
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(new Intent(getActivity(), EditActivity.class));
                    startActivity(intent);

                }
            });
            return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //wordViewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);
        //SubMenu menu1 =menu.addSubMenu("标签");
        //menu1.setHeaderTitle("标签分类");
        menu.addSubMenu(1,0,1,"全部分类");
        menu.addSubMenu(1,1,1,"学习");
        menu.addSubMenu(1,2,1,"生活");
        menu.addSubMenu(1,3,1,"理财");
        /*final ImageView imageView =(ImageView)menu.findItem(R.id.action_delete).getActionView();
        wordViewModel.it.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                imageView.setVisibility(View.VISIBLE);
                myAdapter1 = new MyAdapter(true,wordViewModel,wordViewModel.it);
            }
        });*/
        SearchView searchView = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        //View underline =searchView.findViewById(R.id.app_bar_search);
         //underline.setBackgroundColor(ContextCompat.getColor(searchView.getContext(),R.color.white));
        searchView.setMaxWidth(1900);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                String patten = newText.trim();
                fillteredwords.removeObservers(requireActivity());
                fillteredwords =wordViewModel.findWordsWithPatten(patten);
                fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        myAdapter1.submitList(words);
                    }
                });
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:fillteredwords.removeObservers(requireActivity());
                fillteredwords =wordViewModel.getAllWordsLive();
                fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        myAdapter1.submitList(words);
                    }
                });
                break;
            case 1:fillteredwords.removeObservers(requireActivity());
                fillteredwords =wordViewModel.tagg(2,-1);
                fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        myAdapter1.submitList(words);
                    }
                });
                break;
            case 2:fillteredwords.removeObservers(requireActivity());
                fillteredwords =wordViewModel.tagg(4,-1);
                fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        myAdapter1.submitList(words);
                    }
                });
                break;
            case 3:fillteredwords.removeObservers(requireActivity());
                fillteredwords =wordViewModel.tagg(3,5);
                fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        myAdapter1.submitList(words);
                    }
                });
                break;
        }
        return false;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,0,"删除");
        menu.setGroupCheckable(1,true,true);
        super.onCreateContextMenu(menu,v,menuInfo);
    }
    //上下文菜单被单击时触发该方法
}