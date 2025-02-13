package com.zorina.lk.zorina.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zorina.lk.zorina.R;
import com.zorina.lk.zorina.model.CustomAlert;
import com.zorina.lk.zorina.model.Material;
import com.zorina.lk.zorina.model.MaterialResponse;
import com.zorina.lk.zorina.model.Product;
import com.zorina.lk.zorina.model.Size;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AddProductFragment extends Fragment {

    // Set images
    CardView img1_card, img2_card, img3_card;
    ImageView img1, img2, img3;

    TextView paroductName, adminProductPrice, adminProductQty;
    CheckBox AddCheckBox2Xl, AddCheckBoxXl, AddCheckBoxL, AddCheckBoxM, AddCheckBoxS;


    ActivityResultLauncher<Intent> resultLauncher1, resultLauncher2, resultLauncher3;

    private Uri imageUri1, imageUri2, imageUri3;

    private Spinner materialSpinner;
    Product product = new Product();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        materialSpinner = view.findViewById(R.id.materialSpinner);


        img1_card = view.findViewById(R.id.addProductImageCardView1);
        img2_card = view.findViewById(R.id.addProductImageCardView2);
        img3_card = view.findViewById(R.id.addProductImageCardView3);

        img1 = view.findViewById(R.id.addproductImageView1);
        img2 = view.findViewById(R.id.addproductImageView2);
        img3 = view.findViewById(R.id.addproductImageView3);

        // Register activity result launchers before using them
        registerImageResultHandlers();

        // Set click listeners
        img1_card.setOnClickListener(view1 -> pickImage(1));
        img2_card.setOnClickListener(view1 -> pickImage(2));
        img3_card.setOnClickListener(view1 -> pickImage(3));
        paroductName = view.findViewById(R.id.adminProductName);
        adminProductPrice = view.findViewById(R.id.adminProductPrice);
        adminProductQty = view.findViewById(R.id.adminProductQty);
        AddCheckBox2Xl = view.findViewById(R.id.AddCheckBox2Xl);
        AddCheckBoxXl = view.findViewById(R.id.AddCheckBoxXl);
        AddCheckBoxL = view.findViewById(R.id.AddCheckBoxL);
        AddCheckBoxM = view.findViewById(R.id.AddCheckBoxM);
        AddCheckBoxS = view.findViewById(R.id.AddCheckBoxS);

        CustomAlert alerts = new CustomAlert();
        Size sizes = new Size();

        getMaterilas(view);

        Button addProductbutton = view.findViewById(R.id.AddProductButton);
        addProductbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked2Xl = AddCheckBox2Xl.isChecked();
                boolean isCheckedXl = AddCheckBoxXl.isChecked();
                boolean isCheckedL = AddCheckBoxL.isChecked();
                boolean isCheckedM = AddCheckBoxM.isChecked();
                boolean isCheckedS = AddCheckBoxS.isChecked();


                if (product.getMaterial() == null) {
                    alerts.showErrorAlert(getContext(), "Select Product Material");
                } else if (paroductName.getText().toString().isEmpty()) {
                    alerts.showErrorAlert(getContext(), "Product Name is Empty");
                } else if (adminProductPrice.getText().toString().isEmpty()) {
                    alerts.showErrorAlert(getContext(), "Product Price is Empty");
                } else if (adminProductQty.getText().toString().isEmpty()) {
                    alerts.showErrorAlert(getContext(), "Product Qty is Empty");
                } else if (!(isChecked2Xl || isCheckedXl || isCheckedL || isCheckedM || isCheckedS)) {
                    alerts.showErrorAlert(getContext(), "Select Product Size");

                } else if (imageUri1 == null || imageUri2 == null|| imageUri3 == null) {
                    alerts.showErrorAlert(getContext(), "Product Images is Empty");

                } else {
                    product.setProductName(paroductName.getText().toString());
                    product.setProductPrice(Double.parseDouble(adminProductPrice.getText().toString()));
                    product.setProductQty(Integer.parseInt(adminProductQty.getText().toString()));
                    product.setProductCode(paroductName.getText().toString() + 1);
                    product.setProductStatus(1);
                    if (isChecked2Xl) {
                        sizes.setXxl(1);
                    }
                    if (isCheckedXl) {
                        sizes.setXl(2);

                    }
                    if (isCheckedL) {
                        sizes.setL(3);

                    }
                    if (isCheckedM) {
                        sizes.setM(4);

                    }
                    if (isCheckedS) {
                        sizes.setS(5);
                    }

//                    Log.i("App 15", String.valueOf(product.getMaterial().getId()));
//                    Log.i("App 15", String.valueOf(product.getProductName()));
//                    Log.i("App 15", String.valueOf(product.getProductPrice()));
//                    Log.i("App 15", String.valueOf(product.getProductQty()));
//                    Log.i("App 15", String.valueOf(sizes.getXxl()));
//                    Log.i("App 15", String.valueOf(sizes.getXl()));
//                    Log.i("App 15", String.valueOf(sizes.getL()));
//                    Log.i("App 15", String.valueOf(sizes.getM()));
//                    Log.i("App 15", String.valueOf(sizes.getS()));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClient client = new OkHttpClient();
                            Gson gson = new Gson();
                            JsonObject requestObj = new JsonObject();

                            requestObj.add("product", gson.toJsonTree(product));
                            requestObj.add("SizeList", gson.toJsonTree(sizes));

                            // Convert JSON to RequestBody
                            RequestBody jsonBody = RequestBody.create(gson.toJson(requestObj), MediaType.parse("application/json; charset=utf-8"));



                            // Convert images to files
                            File file1 = getFileFromUri(imageUri1,"temp_image1.jpg");
                            File file2 = getFileFromUri(imageUri2,"temp_image2.jpg");
                            File file3 = getFileFromUri(imageUri3,"temp_image3.jpg");



                            MultipartBody.Builder multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                            multipartBody.addFormDataPart("data", "data.json", jsonBody);

                            if (file1 != null) {
                                multipartBody.addFormDataPart("image1", file1.getName(),
                                        RequestBody.create(file1, MediaType.parse("image/*")));
                            }
                            if (file2 != null) {
                                multipartBody.addFormDataPart("image2", file2.getName(),
                                        RequestBody.create(file2, MediaType.parse("image/*")));
                            }

                            if (file3 != null) {
                                multipartBody.addFormDataPart("image3", file3.getName(),
                                        RequestBody.create(file3, MediaType.parse("image/*")));
                            }



                            Request request = new Request.Builder()
                                    .url("https://tick-crucial-gently.ngrok-free.app/zorina/addProduct")
                                    .post(multipartBody.build())
                                    .build();

                            try {
                                Response response = client.newCall(request).execute();
                                String respText = response.body().string();

                                Log.i("App 15", respText);
                                JsonObject responseText = gson.fromJson(respText, JsonObject.class);

                                if (responseText.get("status").getAsString().equals("success")) {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            alerts.showSuccessAlert(getContext(), "Product Added Successfully");
                                            clearComponents();
                                        }
                                    });
                                } else {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            alerts.showErrorAlert(getContext(), "Product Added Failed");
                                        }
                                    });
                                }


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }


                        }
                    }).start();


                }
            }
        });
        return view;
    }

    private File getFileFromUri(Uri uri,String name) {
        File file = null;
        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
            file = new File(getContext().getCacheDir(),  name);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void clearComponents() {
        paroductName.setText("");
        adminProductPrice.setText("");
        adminProductQty.setText("");
        AddCheckBox2Xl.setChecked(false);
        AddCheckBoxXl.setChecked(false);
        AddCheckBoxL.setChecked(false);
        AddCheckBoxM.setChecked(false);
        AddCheckBoxS.setChecked(false);
        materialSpinner.setSelection(0);
        img1.setImageResource(R.drawable.add_product);
        img2.setImageResource(R.drawable.add_product);
        img3.setImageResource(R.drawable.add_product);
    }


    private void getMaterilas(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://tick-crucial-gently.ngrok-free.app/zorina/getMaterialAll")
                        .build();

                try {
                    Response response = httpClient.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw new RuntimeException("Unexpected response: " + response);
                    }

                    String respText = response.body().string();
                    Log.i("App 15", respText);

                    // Parse JSON response
                    Gson gson = new Gson();
                    MaterialResponse materialResponse = gson.fromJson(respText, MaterialResponse.class);
                    List<Material> materialList = materialResponse.getMaterialList();

                    // Extract material names
                    List<String> materialNames = new ArrayList<>();
                    materialNames.add("Select Material"); // Default selection
                    for (Material material : materialList) {
                        materialNames.add(material.getMaterialName());
                        Log.i("App 15", String.valueOf(material.getId()));

                    }

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {


                            // Update the Spinner adapter on the UI thread
                            ArrayAdapter<String> materialArrayAdapter = new ArrayAdapter<>(
                                    getContext(), R.layout.material_item_layout, materialNames
                            );

                            materialSpinner.setAdapter(materialArrayAdapter);
                            materialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedMaterial = parent.getItemAtPosition(position).toString();
//                                    Toast.makeText(requireContext(), "Selected: " + selectedMaterial, Toast.LENGTH_SHORT).show();

                                    for (Material material : materialList) {
                                        if (material.getMaterialName().equals(selectedMaterial)) {
                                            product.setMaterial(material);
                                            break;
                                        }

                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });


                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void pickImage(int imageNumber) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (imageNumber == 1) {
            resultLauncher1.launch(intent);
        } else if (imageNumber == 2) {
            resultLauncher2.launch(intent);
        } else {
            resultLauncher3.launch(intent);
        }
    }

    private void registerImageResultHandlers() {
        // First image
        resultLauncher1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        imageUri1 = result.getData().getData();
                        img1.setImageURI(imageUri1);
                    } else {
                        Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show();
                    }
                });

        // Second image
        resultLauncher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        imageUri2 = result.getData().getData();
                        img2.setImageURI(imageUri2);
                    } else {
                        Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show();
                    }
                });

        // Third image
        resultLauncher3 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        imageUri3 = result.getData().getData();
                        img3.setImageURI(imageUri3);
                    } else {
                        Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
