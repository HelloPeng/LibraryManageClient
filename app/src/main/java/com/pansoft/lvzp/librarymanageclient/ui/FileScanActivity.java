package com.pansoft.lvzp.librarymanageclient.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.adapter.FileAdapter;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.bean.FileItemBean;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityFileScanBinding;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FileScanActivity extends BaseActivity<ActivityFileScanBinding> {

    public static final String SCAN_TYPE_STUDENT = "student_type";
    public static final String SCAN_TYPE_BOOK = "book_type";

    private static final String TAG = FileScanActivity.class.getSimpleName();
    private static final String SCAN_TYPE = "scan_type";

    public static void actionStart(Context context, String scanType) {
        Intent intent = new Intent();
        intent.setClass(context, FileScanActivity.class);
        intent.putExtra(SCAN_TYPE, scanType);
        context.startActivity(intent);
    }

    private String mScanType;
    private Disposable mPermissionsSubscribe;
    private List<FileItemBean> mListData = new ArrayList<>();
    private FileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPermissionsSubscribe.dispose();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_file_scan;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("数据导入");
        openBackIcon();
        RxPermissions permissions = new RxPermissions(this);
        mPermissionsSubscribe = permissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (!aBoolean) {
                            finish();
                        } else {
                            mScanType = getIntent().getStringExtra(SCAN_TYPE);
                            initRecyclerView();
                            initSearchFile();
                        }
                    }
                });

    }

    private void execExcel(String filePath) {
        showProgressDialog("正在解析文件...");
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new HSSFWorkbook(fis);
            String sheetName = null;
            if (SCAN_TYPE_BOOK.equals(mScanType)) {
                sheetName = "book";
            } else if (SCAN_TYPE_STUDENT.equals(mScanType)) {
                sheetName = "student";
            }
            //得到第i个sheet
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                dismissProgressDialog();
                showToast("该文件格式不正确，无法进行读取");
                finish();
                return;
            }
            //得到行的迭代器
            Iterator<Row> rowIterator = sheet.iterator();
            int rowCount = 0;
            //循环每一行
            while (rowIterator.hasNext()) {
                Log.d(TAG, "第" + (rowCount++) + "行  ");
                //得到一行对象
                Row row = rowIterator.next();
                //得到列对象
                Iterator<Cell> cellIterator = row.cellIterator();
                //循环每一列
                while (cellIterator.hasNext()) {
                    //System.out.print("第"+(columnCount++)+"列:  ");
                    //得到单元格对象
                    Cell cell = cellIterator.next();
                    //检查数据类型
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            Log.d(TAG, cell.getStringCellValue() + "   ");
                            break;
                        case NUMERIC:
                            Log.d(TAG, (int) cell.getNumericCellValue() + "   ");
                    }
                } //end of cell iterator
                Log.d(TAG, "\n");
            } //end of rows iterator
            Log.d(TAG, "\nread excel successfully...");
            //close file input stream
            fis.close();
            dismissProgressDialog();
            showToast("解析成功");


        } catch (Exception e) {
            e.printStackTrace();
            showToast("解析失败");
            dismissProgressDialog();
        }

    }

    private void initRecyclerView() {
        mAdapter = new FileAdapter(mListData);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                //ExcelActivity.actionStart(mContext, mListData.get(position).getPath());
                execExcel(mListData.get(position).getPath());
            }
        });
    }


    private void initSearchFile() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File storageDirectory = Environment.getExternalStorageDirectory();
            Observable
                    .just(storageDirectory)
                    .flatMap(new Function<File, ObservableSource<File>>() {
                        @Override
                        public ObservableSource<File> apply(File file) throws Exception {
                            return listFiles(file);
                        }
                    })
                    .map(new Function<File, File>() {
                        @Override
                        public File apply(File o) throws Exception {
                            FileItemBean itemBean = new FileItemBean();
                            itemBean.setName(o.getName());
                            itemBean.setParentPath(o.getParent());
                            itemBean.setSize(formetFileSize(o.length()));
                            itemBean.setPath(o.getAbsolutePath());
                            mListData.add(0, itemBean);
                            return o;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<File>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDataBinding.llProgress.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onNext(File o) {
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            mDataBinding.llProgress.setVisibility(View.GONE);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }

    private File mParentFile;

    /**
     * rxjava递归查询内存中的视频文件
     *
     * @param f
     * @return
     */
    private Observable<File> listFiles(final File f) {
        if (f != null) {
            if (mParentFile == null) {
                sendDir(f);
            }
            if (!f.getAbsolutePath().equals(mParentFile.getAbsolutePath())) {
                sendDir(f);
            }
            if (f.isDirectory()) {
                /* */
                Observable<File> observable = Observable.fromArray(f.listFiles());
                return observable.flatMap(new Function<File, ObservableSource<File>>() {
                    @Override
                    public ObservableSource<File> apply(final File file) throws Exception {
                        return listFiles(file);
                    }
                });
            } else {
                /*filter操作符过滤视频文件,是视频文件就通知观察者**/
                return Observable.just(f).filter(new Predicate<File>() {
                    @Override
                    public boolean test(File file) throws Exception {
                        return isExcel(file);
                    }
                });
            }
        } else {
            return null;
        }
    }

    private ObservableEmitter<String> mEnitter;
    private Observer<String> schedulers = new Observer<String>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String o) {
            mDataBinding.tvDir.setText(o);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    };

    private void sendDir(File f) {
        mParentFile = f;
        if (mEnitter == null) {
            Observable
                    .create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                            mEnitter = emitter;
                            mEnitter.onNext(mParentFile.getAbsolutePath());
                        }
                    })
                    .throttleWithTimeout(2, TimeUnit.MILLISECONDS)
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(schedulers);
        } else {
            mEnitter.onNext(mParentFile.getAbsolutePath());
        }
    }

    private String formetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


    private boolean isExcel(File file) {
        String suffix = getFileSuffix(file);
        return "xls".equalsIgnoreCase(suffix);
    }

    private String getFileSuffix(File file) {
        String fileName = file.getName();
        if (fileName.contains(".")) {
            int lastIndexOf = fileName.lastIndexOf(".");
            return fileName.substring(lastIndexOf + 1);
        }
        return null;
    }
}
