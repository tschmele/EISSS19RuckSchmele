package com.example.foodinprogress.notNeeded;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SearchDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true).setTitle("Suche").setMessage("Hier ist eine Nachricht");

        final EditText input = new EditText(getContext());
        builder.setView(input);

        builder.setNeutralButton("Suchen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String result = input.getText().toString();

                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();

                /*
                * TODO: Obejcte die dem input entsprechen anzeigen
                */

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                /*
                 * TODO: Zurück zur vorherigen Ansicht gehen
                 */

            }
        });

        return builder.create();
    }

}



