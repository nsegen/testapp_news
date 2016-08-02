/**
 * Created by Revotech on 25.07.16.
 */
$(function() {
    $('input[type=file]').bootstrapFileInput();
    $('.file-inputs').bootstrapFileInput();
    function handleFileSelect(evt) {
        var files = evt.target.files; // FileList object

        // Loop through the FileList and render image files as thumbnails.
        for (var i = 0, f; f = files[i]; i++) {

            // Only process image files.
            if (!f.type.match('image.*')) {
                continue;
            }

            var reader = new FileReader();

            // Closure to capture the file information.
            reader.onload = (function(theFile) {
                return function(e) {
                    // Render thumbnail.
                    var span = document.createElement('div');
                    span.setAttribute("class", "row newsImg");
                    span.innerHTML = ['<img class="img-rounded" src="', e.target.result,
                        '" title="', escape(theFile.name), '"/>'].join('');
                    document.getElementById('list').insertBefore(span, null);
                };
            })(f);

            // Read in the image file as a data URL.
            reader.readAsDataURL(f);
        }
        //console.log($('#chosenImg').attr("src"));
        $('#chosenImg').css("display", "none");
    }

    document.getElementById('inputImage').addEventListener('change', handleFileSelect, false);
});
