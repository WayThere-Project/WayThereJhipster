import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IVehicle } from 'app/shared/model/vehicle.model';
import { getEntities as getVehicles } from 'app/entities/vehicle/vehicle.reducer';
import { IDriver } from 'app/shared/model/driver.model';
import { getEntity, updateEntity, createEntity, reset } from './driver.reducer';

export const DriverUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const vehicles = useAppSelector(state => state.vehicle.entities);
  const driverEntity = useAppSelector(state => state.driver.entity);
  const loading = useAppSelector(state => state.driver.loading);
  const updating = useAppSelector(state => state.driver.updating);
  const updateSuccess = useAppSelector(state => state.driver.updateSuccess);

  const handleClose = () => {
    navigate('/driver');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getVehicles({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.driverID !== undefined && typeof values.driverID !== 'number') {
      values.driverID = Number(values.driverID);
    }

    const entity = {
      ...driverEntity,
      ...values,
      vehicle: vehicles.find(it => it.id.toString() === values.vehicle?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...driverEntity,
          vehicle: driverEntity?.vehicle?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="wayThereJhipsterApp.driver.home.createOrEditLabel" data-cy="DriverCreateUpdateHeading">
            <Translate contentKey="wayThereJhipsterApp.driver.home.createOrEditLabel">Create or edit a Driver</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="driver-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('wayThereJhipsterApp.driver.driverID')}
                id="driver-driverID"
                name="driverID"
                data-cy="driverID"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.driver.fname')}
                id="driver-fname"
                name="fname"
                data-cy="fname"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.driver.lname')}
                id="driver-lname"
                name="lname"
                data-cy="lname"
                type="text"
              />
              <ValidatedField label={translate('wayThereJhipsterApp.driver.dob')} id="driver-dob" name="dob" data-cy="dob" type="date" />
              <ValidatedField
                id="driver-vehicle"
                name="vehicle"
                data-cy="vehicle"
                label={translate('wayThereJhipsterApp.driver.vehicle')}
                type="select"
              >
                <option value="" key="0" />
                {vehicles
                  ? vehicles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/driver" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DriverUpdate;
