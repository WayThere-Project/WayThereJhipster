import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRate } from 'app/shared/model/rate.model';
import { getEntity, updateEntity, createEntity, reset } from './rate.reducer';

export const RateUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const rateEntity = useAppSelector(state => state.rate.entity);
  const loading = useAppSelector(state => state.rate.loading);
  const updating = useAppSelector(state => state.rate.updating);
  const updateSuccess = useAppSelector(state => state.rate.updateSuccess);

  const handleClose = () => {
    navigate('/rate');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
    if (values.rateID !== undefined && typeof values.rateID !== 'number') {
      values.rateID = Number(values.rateID);
    }
    if (values.timeOfDay !== undefined && typeof values.timeOfDay !== 'number') {
      values.timeOfDay = Number(values.timeOfDay);
    }
    if (values.vehicleSize !== undefined && typeof values.vehicleSize !== 'number') {
      values.vehicleSize = Number(values.vehicleSize);
    }
    if (values.baseRate !== undefined && typeof values.baseRate !== 'number') {
      values.baseRate = Number(values.baseRate);
    }

    const entity = {
      ...rateEntity,
      ...values,
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
          ...rateEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="wayThereJhipsterApp.rate.home.createOrEditLabel" data-cy="RateCreateUpdateHeading">
            <Translate contentKey="wayThereJhipsterApp.rate.home.createOrEditLabel">Create or edit a Rate</Translate>
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
                  id="rate-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('wayThereJhipsterApp.rate.rateID')}
                id="rate-rateID"
                name="rateID"
                data-cy="rateID"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.rate.timeOfDay')}
                id="rate-timeOfDay"
                name="timeOfDay"
                data-cy="timeOfDay"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.rate.vehicleSize')}
                id="rate-vehicleSize"
                name="vehicleSize"
                data-cy="vehicleSize"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.rate.baseRate')}
                id="rate-baseRate"
                name="baseRate"
                data-cy="baseRate"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/rate" replace color="info">
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

export default RateUpdate;
