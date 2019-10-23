package com.seeyou.toolkit.base;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.seeyou.toolkit.R;
import com.seeyou.toolkit.intfic.Callback;
import com.seeyou.toolkit.intfic.NetWorkCallback;
import com.seeyou.toolkit.view.Action;
import com.seeyou.toolkit.view.LoadView;
import com.seeyou.toolkit.view.Options;

import java.util.List;

/**
 * @author sumn
 * date 2019/10/21
 */
public class ListProcessFactory<T extends BaseModel> {
    private SmartRefreshLayout mRefreshView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LoadView mLoadView;
    private Options emptyOptions, errorOptions;
    private boolean enableRefresh = true;
    private Callback<ListProcessFactory<T>> loadCallback;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataSource<T> mDataSource;

    private BaseListCallBack baseListCallBack;

    public ListProcessFactory() {
        baseListCallBack = new BaseListCallBack(this);
    }


    public ListProcessFactory<T> bind(View root) {
        mLoadView = root.findViewById(R.id.loadView);
        if (emptyOptions != null) {
            mLoadView.emptyOptions(emptyOptions);
        }
        if (errorOptions != null) {
            mLoadView.errorOptions(errorOptions);
        }
        mRefreshView = root.findViewById(R.id.refreshView);
        if (mRefreshView != null) {
            mRefreshView.setOnRefreshListener(refreshLayout -> {
                loadCallback.call(this);
            });
            mRefreshView.setEnableRefresh(enableRefresh);

        }
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(mLayoutManager != null ? mLayoutManager : new LinearLayoutManager(root.getContext()));
        mRecyclerView.setAdapter(mAdapter);

        return this;
    }


    public void loading() {
        mLoadView.loading();
    }


    public void setData(List<T> data) {
        mDataSource.setData(data);
        if (isEmpty()) {
            mLoadView.empty();
        } else {
            mLoadView.content();
        }
    }


    public void error(String message, View.OnClickListener retry) {
        mDataSource.clear();
        if (isEmpty()) {
            mLoadView.error(new Options().action(new Action("重试", retry)));
        } else {
            mLoadView.content();
            Toast.makeText(mLoadView.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }


    public void empty() {
        mDataSource.clear();
        if (isEmpty()) {
            mLoadView.empty();
        } else {
            mLoadView.content();
        }
    }


    public boolean isEmpty() {
        return mDataSource.size() <= 0;
    }

    public ListProcessFactory<T> loadView(LoadView loadingLayout) {
        this.mLoadView = loadingLayout;
        mLoadView.emptyOptions(emptyOptions);
        return this;
    }

    @Nullable
    public SmartRefreshLayout refreshLayout() {
        return mRefreshView;
    }

    public ListProcessFactory<T> refreshLayout(SmartRefreshLayout refreshLayout) {
        mRefreshView = refreshLayout;
        if (mRefreshView != null) {
            mRefreshView.setOnRefreshListener(refreshLayout1 -> {
                loadCallback.call(this);
            });
            mRefreshView.setEnableRefresh(enableRefresh);
        }
        return this;
    }


    public RecyclerView recyclerView() {
        return mRecyclerView;
    }

    public ListProcessFactory<T> recyclerView(@NonNull RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setLayoutManager(mLayoutManager != null ? mLayoutManager : new LinearLayoutManager(recyclerView.getContext()));
        mRecyclerView.setAdapter(mAdapter);
        return this;
    }

    public Callback<ListProcessFactory<T>> loadCallback() {
        return loadCallback;
    }

    @SuppressWarnings("all")
    public ListProcessFactory<T> adapter(RecyclerView.Adapter adapter) {
        if (adapter instanceof DataSource) {
            mDataSource = (DataSource<T>) adapter;
            this.mAdapter = adapter;
        } else {
            throw new IllegalArgumentException("Your adapter must implements 'DataSource'");
        }
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(adapter);
        }
        return this;
    }

    public ListProcessFactory<T> layoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(layoutManager);
        }
        return this;
    }


    public ListProcessFactory<T> refresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        if (mRefreshView != null) {
            mRefreshView.setEnableRefresh(enableRefresh);
        }
        return this;
    }

    public ListProcessFactory<T> empty(Options options) {
        this.emptyOptions = options;
        if (mLoadView != null) {
            mLoadView.emptyOptions(options);
        }
        return this;
    }


    public ListProcessFactory<T> error(Options options) {
        this.errorOptions = options;
        if (mLoadView != null) {
            mLoadView.errorOptions(options);
        }
        return this;
    }

    public ListProcessFactory<T> loadCallback(Callback<ListProcessFactory<T>> loadCallback) {
        this.loadCallback = loadCallback;
        return this;
    }

    public ListProcessFactory<T> autoLoad() {
        this.loadCallback.call(this);
        return this;
    }

    public BaseListCallBack callBack() {
        return this.baseListCallBack;
    }

    public class BaseListCallBack implements NetWorkCallback<T> {
        private ListProcessFactory<T> page;

        BaseListCallBack(@NonNull ListProcessFactory<T> page) {
            this.page = page;
        }


        private void updateRefreshView(boolean success) {
            if (page.refreshLayout() != null) {
                page.refreshLayout().finishRefresh(300, success, false);
                page.refreshLayout().postDelayed(() -> page.refreshLayout(), 310);
            }
        }


        @Override
        public void Success(T t) {
            if (t != null) {
                List<T> list = (List<T>) t.getData();
                if (list != null && list.size() > 0) {
                    page.setData(list);
                    updateRefreshView(true);
                } else {
                    page.empty();
                    updateRefreshView(false);
                }
            } else {
                page.empty();
                updateRefreshView(false);
            }

        }

        @Override
        public void Error(String msg) {
            if (page.refreshLayout() != null) {
                page.error(msg, v -> page.autoLoad());
                page.refreshLayout().finishRefresh(false);
            }
        }

        @Override
        public void Loading() {
            page.loading();
        }


    }

}
