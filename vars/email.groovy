def call(Map email){

    emailext(
       subject: "✅ SUCCESS | The Job - ${JOB_NAME}has passed with #${BUILD_NUMBER}",
       mimeType: 'text/html',
       to: email.to,
       body: """
            <p>Hello Team,</p>

            <p> The Jenkins Job Completed Successfully. Please find the details below:</p>

            <p><b>Job Name:</b> ${JOB_NAME}</p>
            <p><b>Build Number:</b> ${BUILD_NUMBER}</p>
            <p><b>Build Status:</b> ${currentBuild.currentResult}</p>
            <p><b>Build URL:</b> <a href="${BUILD_URL}">${BUILD_URL}</a></p>

            <p>The build has completed successfully and is ready for the next stage </p>

            <p>Regards,</p>
            <p>DevOpsTeam</p>


            <p>Note: This is an auto-generated email. Please do not reply to this email.</p>         
       """
    )

}
