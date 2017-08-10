package articlesapp.zack.com.lasttour;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;



/**
 * A simple {@link Fragment} subclass.

 */
public class EntertainmentFragment extends Fragment {
    public static final String NAME = "Entertainment";
    private ListAdapter EntertainmentAdapter;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef;
    DatabaseReference db;
    StorageReference firebaseStorage;
    FirebaseHelperEntertainment helper;
    EntertainmentAdapter adapter;
    ListView lvEntertainment;
    EditText edName, edDescription;
    ImageView ivImage;
    Button btnSave, btnBrowser, btnUpload;
    int PICK_IMAGE_REQUEST = 111;
    Uri filePath;
    String pojoUri;
    ProgressDialog pd;
    private static final int GALLERY_INTENT = 2;
    ArrayList<Entertainment> entertainments;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_entertainment, container, false);
        lvEntertainment = (ListView) v.findViewById(R.id.lvEntertainments);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Uploading....");
        db = FirebaseDatabase.getInstance().getReference();
        firebaseStorage = FirebaseStorage.getInstance().getReference().child("toursoweto_photos");
        FloatingActionButton floatingActionButton = (FloatingActionButton) v.findViewById(R.id.floatingActionButtonentertainment);
        helper = new FirebaseHelperEntertainment(db);


        adapter = new EntertainmentAdapter(getActivity(), helper.retrive());

        lvEntertainment.setAdapter((ListAdapter) adapter);
        entertainments = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diplayInputDialog();
            }
        });
        return v;


    }



    private void diplayInputDialog() {
        Dialog d = new Dialog(getActivity());
        d.setTitle("Save To FireBase");
        d.setContentView(R.layout.input_dialog);

        edDescription = (EditText) d.findViewById(R.id.edDescrption);
        edName = (EditText) d.findViewById(R.id.edName);
        ivImage = (ImageView) d.findViewById(R.id.ivImage);
        btnSave = (Button) d.findViewById(R.id.btnSaved);
        btnBrowser = (Button) d.findViewById(R.id.btnBrowser);
        btnUpload = (Button) d.findViewById(R.id.btnUpload);

        firebaseStorage = FirebaseStorage.getInstance().getReference();
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,GALLERY_INTENT);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null) {
                    pd.show();

                    StorageReference childRef = firebaseStorage.child(filePath.getLastPathSegment());

                    //uploading the image
                    UploadTask uploadTask = childRef.putFile(filePath);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            @SuppressWarnings("VisibleForTests")     Uri uir = taskSnapshot.getDownloadUrl();
                            pojoUri = uir.toString();
                            Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(getActivity(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Select an image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString().trim();
                String description = edDescription.getText().toString().trim();
                edName.setTextIsSelectable(true);


                Entertainment entertainment = new Entertainment();
                entertainment.setName(name);
                entertainment.setDescription(description);
                entertainment.setUrl(pojoUri);
                if (name != null && name.length() > 0) {
                    if (helper.save(entertainment)) {
                        edDescription.setText("");
                        edName.setText("");

                        adapter = new EntertainmentAdapter(getActivity(), helper.retrive());
                        lvEntertainment.setAdapter(adapter);
                    }

                }


            }
        });

        d.show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();


            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);

                //Setting image to ImageView
                ivImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}