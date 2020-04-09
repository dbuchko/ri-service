cf create-service google-storage standard ri-gcs-5 -c '{"location":"northamerica-northeast1"}'
cf bind-service ri-service ri-gcs-2 -c '{"role":"storage.objectAdmin"}'
